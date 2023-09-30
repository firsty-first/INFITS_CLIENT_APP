<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];
$sectionNo = $_POST['sectionNo'];
$newAnswer=$_POST['newAnswer'];



// $clientuserID = 'dev';
// $sectionNo = 'section1';
// $newAnswer= 1;


$sql = "Select clientuserID from sectionProgress where clientuserID = '$clientuserID';";

$result = mysqli_query($conn, $sql);

$row =mysqli_fetch_assoc($result);

//echo $row;
if($row == NULL)
{
  
  $insert = "INSERT INTO sectionProgress VALUES( '$clientuserID', 0,0,0,0,0,0 );";
  mysqli_query($conn, $insert);

}
else{
  $update = "UPDATE sectionProgress SET $sectionNo = '$newAnswer' WHERE clientuserID = '$clientuserID';";
  mysqli_query($conn, $update);
}


?>