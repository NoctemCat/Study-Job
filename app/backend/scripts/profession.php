<?php 
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
$conn = $db->con;

$response = array();

if(isset($_GET['apicall'])){
    switch($_GET['apicall']){
    case 'get_profession':
        if(isTheseParametersAvailable(array('id')))
        {
            $id = $_POST['id']; 

            $stmt = $conn->prepare("SELECT science, code, profession_specialty, qualifications, persona_id FROM professions WHERE id = ?"); 
            $stmt->bind_param("s",$id);
            $stmt->execute();
            $stmt->bind_result($science, $code, $prof_spec, $qual, $persona_id);
            $stmt->fetch();

            $prof = array(
                'id'=>$id,
                'science'=>$science,
                'code'=>$code,
                'prof_spec'=>$prof_spec,
                'qual'=>$qual,
                'persona_id'=>$persona_id
            );

            $stmt->close();

            $response['error'] = false; 
            $response['message'] = 'Successfully got profession'; 
            $response['profession'] = $prof; 
        }
        else
        {
            $response['error'] = true; 
            $response['message'] = 'required parameters are not available'; 
        }
        break;

    case 'get_by_persona':
        if(isTheseParametersAvailable(array('persona_id')))
        {
            $persona_id = $_POST['persona_id'];
            $response["professions"] = array();

            //creating the query 
            $stmt = $conn->prepare("SELECT * FROM professions WHERE `persona_id` = '$persona_id'");
            $stmt->execute();
            $stmt->store_result();
            
            $stmt->bind_result($id, $science, $code, $prof_spec, $qual, $persona_id);
            while($stmt->fetch())
            {
                $profession = array(
                    'id'=>$id,
                    'science'=>$science,
                    'code'=>$code,
                    'prof_spec'=>$prof_spec,
                    'qual'=>$qual,
                    'persona_id'=>$persona_id
                );

                array_push($response["professions"], $profession);
            }
            $response['error'] = false; 
            $response['message'] = 'Got all proffesions';
        }
        else
        {
            $response['error'] = true; 
            $response['message'] = 'required parameters are not available'; 
        }
        break;

    case 'get':
        $response["professions"] = array();

        //creating the query 
        $stmt = $conn->prepare("SELECT * FROM professions LIMIT 25");
        $stmt->execute();
        $stmt->store_result();
        
        $stmt->bind_result($id, $science, $code, $prof_spec, $qual, $persona_id);
        while($stmt->fetch())
        {
            $profession = array(
                'id'=>$id,
                'science'=>$science,
                'code'=>$code,
                'prof_spec'=>$prof_spec,
                'qual'=>$qual,
                'persona_id'=>$persona_id
            );

            array_push($response["professions"], $profession);
        }
        $response['error'] = false; 
        $response['message'] = 'Got top 25 proffesions';

        break;
    default: 
        $response['error'] = true; 
        $op = $_GET['apicall'];
        $response['message'] = "Invalid Operation $op";
    }
}else{
    $response['error'] = true; 
    $response['message'] = 'Invalid API Call';
}

echo json_encode($response);

function isTheseParametersAvailable($params)
{
    //traversing through all the parameters 
    foreach($params as $param)
    {
        if(!isset($_POST[$param]))
        {
            return false; 
        }
    }
    return true; 
}
?>