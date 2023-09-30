<?php

$conn=new mysqli("www.db4free.net","infits_free_test","EH6.mqRb9QBdY.U","infits_db");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$clientuserID = $_POST['clientuserID'];
$FavouriteFoodName=$_POST['FavouriteFoodName'];
// $clientuserID="test";
// $FavouriteFoodName="poha";

$sql="DELETE FROM `favourite_food_items` WHERE clientID='$clientuserID' and nameofFoodItem='$FavouriteFoodName'";
if (mysqli_query($conn,$sql)) {
    echo "success";
  }
  else{
    echo "failed";
  }
?>