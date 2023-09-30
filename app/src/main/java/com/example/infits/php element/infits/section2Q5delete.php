<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];
$newAnswer=$_POST['newAnswer'];


// $clientuserID = 'dev';
// $question='Email';
// $newAnswer = ["abcd@gmail.com", "diabetes", "abc"];

$sql = "DELETE FROM section2Q5 WHERE clientuserID = '$clientuserID';";
$result = mysqli_query($conn, $sql);


?>