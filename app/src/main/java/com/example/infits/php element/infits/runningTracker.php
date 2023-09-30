<?php

require "connect.php";


$userID = $_POST['client_id'];
$clientuserID = $_POST['clientuserID'];
$distance = $_POST['distance'];
$calories = $_POST['calories'];
$runtime = $_POST['runtime'];
$goal = $_POST['goal'];
$date = date('Y-m-d',strtotime($_POST['date']));
$duration=$_POST['duration'];
$dateandtime=$_POST['dateandtime'];


$sql = "update runningtracker set distance='$distance',calories = '$calories',runtime = '$runtime' where date = '$date' and client_id = '$userID'";
//$sql2 = "INSERT INTO `calorie_burnt`(`client_id`, `clientuserID`, `dietitian_id`, `dietitianuserID`, `activity_name`, `calorie_burnt`, `duration`, `dateandtime`) 
//VALUES ('$userID','$clientuserID','19','infits','cycling','$calories','$duration','$dateandtime')";
  
if (mysqli_query($conn,$sql) || mysqli_query($conn,$sql2)) {
  echo "updated";
  //echo "Inserted";
}
else{
  echo "error";
}
?>