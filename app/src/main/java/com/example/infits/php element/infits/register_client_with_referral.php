<?php

$conn = new mysqli("www.db4free.net", "infits_free_test", "EH6.mqRb9QBdY.U", "infits_db");
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
$email = $_POST['email'];
$password = $_POST['password'];
$userID = $_POST['userID'];
$name = $_POST['name'];
$mobile = $_POST['phone'];
$age = $_POST['age'];
$gender=$_POST['gender'];
$height = $_POST['height'];
$weight = $_POST['weight'];
$referredCode = $_POST['referredCode'];
$verification = $_POST['verification'];
$dietitian_id = $_POST['dietitian_id'];
$dietitianuserID = $_POST['dietitianuserID'];

//for referral 
$clientID = $_POST['userid'];
$referralCode = $_POST['referralCode'];
$activeUsers = $_POST['activeUsers'];


$sql = "
INSERT INTO client (clientuserID,password,name,email,mobile,dietitian_id,dietitianuserID,gender,age,verification,height,weight) VALUES ('$userID','$password',
'$name','$email','$mobile','$dietitian_id','$dietitianuserID','$gender','$age','$verification','$weight','$height');
  INSERT INTO `referral_table`(`clientuserID`, `referralCode`, `activeUsers`) VALUES ('$clientID','$referralCode','$activeUsers');
    ";

    if ($conn->multi_query($sql)) {
      do {
        // Consume all results
        if ($result = $conn->store_result()) {
          $result->free();
        }
      } while ($conn->more_results() && $conn->next_result());
    

         $fetchExistingID = "SELECT referred_clientID FROM client WHERE clientuserID = (SELECT clientuserID FROM referral_table WHERE referralCode = '$referredCode')COLLATE utf8mb4_0900_ai_ci";
         $existingIDResult = $conn->query($fetchExistingID);

        if ($existingIDResult) {
             $existingIDRow = $existingIDResult->fetch_assoc();
             $existingReferredClientID = $existingIDRow['referred_clientID'];

            if (empty($existingReferredClientID)) {
                $newReferredClientID = $clientID;
                 } else {
                 $newReferredClientID = $existingReferredClientID . ',' . $clientID;
                  }

           $updateReferredClientIDQuery = "UPDATE client SET referred_clientID = '$newReferredClientID' WHERE clientuserID = (SELECT clientuserID FROM referral_table WHERE referralCode = '$referredCode')COLLATE utf8mb4_0900_ai_ci";

           if ($conn->query($updateReferredClientIDQuery) === TRUE) {
           echo "Success";
           } else {
            echo "Error updating referred_clientID: " . $conn->error;
           }

         $existingIDResult->free();
                } else {
         echo "Error fetching existing referred_clientID: " . $conn->error;
        }
    } else {
      echo "Error in Selecting: " . $conn->error;
    }

    ?> 