<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];
$table = $_POST['table'];


// $clientuserID = 'dev';
// $table='section1Q1';



$sql = "select answer FROM $table WHERE clientuserID = '$clientuserID';";

$result = mysqli_query($conn, $sql);


$full = array();
$emp = array();

$row =mysqli_fetch_assoc($result);

$temp['answer'] = $row['answer'];
$full[] = $temp;


echo json_encode(['answer' => $full]);



?>