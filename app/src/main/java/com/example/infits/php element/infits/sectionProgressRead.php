<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// $clientuserID = $_POST['clientuserID'];


$clientuserID = 'dev';




$sql = "select * FROM sectionProgress WHERE clientuserID = '$clientuserID';";

$result = mysqli_query($conn, $sql);


$full = array();
$emp = array();



while ($row = $result->fetch_assoc()) {
    $temp['section1'] = $row['section1'];
    $temp['section2'] = $row['section2'];
    $temp['section3'] = $row['section3'];
    $temp['section4'] = $row['section4'];
    $temp['section5'] = $row['section5'];
    $temp['section6'] = $row['section6'];
    $full[] = $temp;
  }




echo json_encode(['answer' => $full]);



?>