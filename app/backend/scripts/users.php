<?php 
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
$conn = $db->con;

$response = array();

if(isset($_GET['apicall'])){
    switch($_GET['apicall']){
    case 'signup':
        if(isTheseParametersAvailable(array('login','password','name', 'age', 'role')))
        {
            $login = $_POST['login']; 
            $password = md5($_POST['password']);
            $name = $_POST['name']; 
            $age = $_POST['age'];
            $role = $_POST['role'];

            $school_grade = null;
            $student_place = null; 
            $student_group = null; 
            $teacher_place = null;
            $teacher_position = null;

            if(isset($_POST['school_grade']))
            {
                $school_grade = $_POST['school_grade']; 
            }
            if(isset($_POST['student_place']))
            {
                $student_place = $_POST['student_place']; 
            }
            if(isset($_POST['student_group']))
            {
                $student_group = $_POST['student_group']; 
            }
            if(isset($_POST['teacher_place']))
            {
                $teacher_place = $_POST['teacher_place']; 
            }
            if(isset($_POST['teacher_position']))
            {
                $teacher_position = $_POST['teacher_position']; 
            }

            $stmt = $conn->prepare("SELECT id FROM users WHERE login = ?");
            $stmt->bind_param("s", $login);
            $stmt->execute();
            $stmt->store_result();

            if($stmt->num_rows > 0)
            {
                $response['error'] = true;
                $response['message'] = 'User already registered';
                $stmt->close();
            }
            else
            {
                $stmt = $conn->prepare("INSERT INTO users (login, password, name, age, role, school_grade, student_place, student_group, teacher_place, teacher_position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                $stmt->bind_param("ssssssssss", $login, $password, $name, $age, $role, $school_grade, $student_place, $student_group, $teacher_place, $teacher_position);

                if($stmt->execute())
                {
                    $stmt = $conn->prepare("SELECT id, id, login, password, name, age, role, school_grade, student_place, student_group, teacher_place, teacher_position, personality FROM users WHERE login = ?"); 
                    $stmt->bind_param("s",$login);
                    $stmt->execute();
                    $stmt->bind_result($userid, $id, $login, $password, $name, $age, $role, $school_grade, $student_place, $student_group, $teacher_place, $teacher_position, $personality);
                    $stmt->fetch();

                    $user = array(
                        'id'=>$id, 
                        'login'=>$login, 
                        'name'=>$name,
                        'age'=>$age,
                        'role'=>$role,
                        'school_grade'=>$school_grade, 
                        'student_place'=>$student_place,
                        'student_group'=>$student_group,
                        'teacher_place'=>$teacher_place,
                        'teacher_position'=>$teacher_position,
                        'personality'=>$personality
                    );

                    $stmt->close();

                    $response['error'] = false; 
                    $response['message'] = 'User registered successfully'; 
                    $response['user'] = $user; 
                }
            }
        }
        else
        {
        $response['error'] = true; 
        $response['message'] = 'required parameters are not available'; 
        }

        break; 
    case 'login':
        if(isTheseParametersAvailable(array('login', 'password')))
        {
            $login = $_POST['login'];
            $password = md5($_POST['password']); 

            $stmt = $conn->prepare("SELECT id, login, password, name, age, role, school_grade, student_place, student_group, teacher_place, teacher_position, personality FROM users WHERE login = ? AND password = ?");
            $stmt->bind_param("ss",$login, $password);

            $stmt->execute();

            $stmt->store_result();

            if($stmt->num_rows > 0){
                $stmt->bind_result($id, $login, $password, $name, $age, $role, $school_grade, $student_place, $student_group, $teacher_place, $teacher_position, $personality);
                $stmt->fetch();

                $user = array(
                    'id'=>$id, 
                    'login'=>$login, 
                    'name'=>$name,
                    'age'=>$age,
                    'role'=>$role,
                    'school_grade'=>$school_grade, 
                    'student_place'=>$student_place,
                    'student_group'=>$student_group,
                    'teacher_place'=>$teacher_place,
                    'teacher_position'=>$teacher_position,
                    'personality'=>$personality
                );

                $response['error'] = false; 
                $response['message'] = 'Login successfull'; 
                $response['user'] = $user; 
            }
            else
            {
                $response['error'] = true; 
                $response['message'] = 'Invalid username or password';
            }
        }
        break;
    case 'update_test':
        if(isTheseParametersAvailable(array('id', 'personality')))
        {
            $id = $_POST['id'];
            $personality = $_POST['personality'];

            $stmt = $conn->prepare("UPDATE users SET `personality` = ? WHERE id = ?");
            $stmt->bind_param("ss", $personality, $id);            
            
            if($stmt->execute())
            {
                $response['error'] = false; 
                $response['message'] = 'Updated personality'; 
            }
            else
            {
                $response['error'] = true; 
                $response['message'] = 'Failed to update personality'; 
            }
        }
        break;
    default: 
        $response['error'] = true; 
        $response['message'] = 'Invalid Operation Called';
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