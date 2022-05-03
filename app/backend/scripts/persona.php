<?php 
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
$conn = $db->con;

$response = array();

if(isset($_GET['apicall'])){
    switch($_GET['apicall']){
    case 'get_persona':
        if(isTheseParametersAvailable(array('id')))
        {
            $id = $_POST['id']; 

            $stmt = $conn->prepare("SELECT name, description FROM personalities WHERE id = ?"); 
            $stmt->bind_param("s",$id);
            $stmt->execute();
            $stmt->bind_result($name, $desc);
            $stmt->fetch();

            $pesona = array(
                'id'=>$id,
                'name'=>$name,
                'desc'=>$desc
            );

            $stmt->close();

            $response['error'] = false; 
            $response['message'] = 'Successfully got personality'; 
            $response['persona'] = $pesona; 
        }
        else
        {
            $response['error'] = true; 
            $response['message'] = 'required parameters are not available'; 
        }
        break;
    case 'get':
        $response["personas"] = array();

        //creating the query 
        $stmt = $conn->prepare("SELECT * FROM personalities");
        $stmt->execute();
        $stmt->store_result();
        
        $stmt->bind_result($id, $name, $desc);
        while($stmt->fetch())
        {
            $persona = array(
                'id'=>$id,
                'name'=>$name,
                'desc'=>$desc
            );

            array_push($response["personas"], $persona);
        }
        $response['error'] = false; 
        $response['message'] = 'Got personas';

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