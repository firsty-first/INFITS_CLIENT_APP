<?php

require "connect.php";

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];
$newAnswer=$_POST['newAnswer'];


// $clientuserID = 'dev';
// $question='Email';
// $newAnswer =  "diabetes";





    
      
$insert = "INSERT INTO section2Q8 VALUES( '$clientuserID', '$newAnswer' );";
mysqli_query($conn, $insert);
    


?>