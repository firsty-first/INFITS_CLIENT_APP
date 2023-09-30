<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];


// $clientuserID = 'dev';
// $question='Email';



$sql = "select answer FROM section2Q8 WHERE clientuserID = '$clientuserID';";

$result = mysqli_query($conn, $sql);


$full = array();
$emp = array();



while ($row = $result->fetch_assoc()) {
    $temp['answer'] = $row['answer'];
    $full[] = $temp;
  }




echo json_encode(['answer' => $full]);



?>