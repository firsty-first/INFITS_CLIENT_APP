<?php
// Create 4 variables to store these information
$server="www.db4free.net";
$username="infits_free_test";
$password="EH6.mqRb9QBdY.U";
$database = "infits_db";
// Create connection
$conn = new mysqli($server, $username, $password, $database);
// Check connection
if ($conn->connect_error) {
  echo("Connection failed: " . $conn->connect_error);
}  
?>