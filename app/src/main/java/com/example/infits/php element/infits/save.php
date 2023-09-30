<?php
require "connect.php";



$clientuserID = $_POST['clientuserID'];
$name = $_POST['name'];
$email = $_POST['email'];
$mobile = $_POST['mobile'];
$gender = $_POST['gender'];
$age = $_POST['age'];
$location = $_POST['location'];
$height = $_POST['height'];
$weight = $_POST['weight'];

$sql = "UPDATE client SET name='$name', email='$email', mobile='$mobile', gender='$gender', age='$age', location='$location', height='$height', weight='$weight' WHERE clientuserID='$clientuserID'";

if ($conn->query($sql) === TRUE) {
    echo "Success";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>