<?php

$conn=new mysqli("www.db4free.net","infits_free_test","EH6.mqRb9QBdY.U","infits_db");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$clientuserID = $_POST['clientuserID'];
$FavouriteFoodName=$_POST['FavouriteFoodName'];
$calorie=$_POST['calorie'];
$protein=$_POST['protein'];
$carb=$_POST['carb'];
$fat=$_POST['fat'];


// $clientuserID="test";
// $FavouriteFoodName="poha";
// $calorie="130";
// $protein="2.5";
// $fibre="1.1";
// $carb="26.6";
// $fat="2.4";

$sql="INSERT INTO `favourite_food_items`(`clientID`, `nameofFoodItem`, `calorie`, `protein`, `carb`, `fat`) VALUES ('$clientuserID','$FavouriteFoodName','$calorie','$protein','$carb','$fat')";
if (mysqli_query($conn,$sql)) {
    echo "success";
  }
  else{
    echo "failed";
  }
?>