<?php 
require "connect.php";
// Create connection
$conn=mysqli_connect($server,$username,$password,$database);

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

	$dietitianuserID = $_POST['dietitianuserID'];
  $clientuserID = $_POST['clientuserID'];
	$message = $_POST['message'];
  $type = $_POST['type'];
  $sentBy = $_POST['sentBy'];
  //$filename = $_POST['filename'];
  $time = $_POST['time'];
  if($type != "text"){
    $encoded = $message;
    $message = "";
  }
$stmnt = $conn -> prepare("INSERT INTO `messages`(`dietitianuserID`, `clientuserID`, `message`, `messageBy`, `time`) VALUES (?,?,?,?,?)");

$stmnt->bind_param("sssss",$dietitianuserID,$clientuserID,$message,$sentBy,$time);

if($stmnt->execute()){
  if($type!="text"){
    file_put_contents("upload/Messages/$dietitianuserID$clientuserID$filename",base64_decode($encoded));
  }
echo "success";
}

else {
echo "failure";
}

?>
