<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];
$newAnswer=$_POST['newAnswer'];
$table = $_POST['table'];


// $clientuserID = 'dev';
// $question='Email';
// $newAnswer = "abcd@gmail.com";



$sql = "Select answer from $table where clientuserID = '$clientuserID';";

$result = mysqli_query($conn, $sql);

$row =mysqli_fetch_assoc($result);

//echo $row;
if($row == NULL)
{
  
  $insert = "INSERT INTO $table VALUES( '$clientuserID', '$newAnswer' );";
  mysqli_query($conn, $insert);

}else{
  $update = "UPDATE $table SET answer = '$newAnswer' WHERE clientuserID = '$clientuserID';";
  mysqli_query($conn, $update);
}


?>