<?php
$conn = new mysqli("www.db4free.net", "infits_free_test", "EH6.mqRb9QBdY.U", "infits_db");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$clientuserID = $_POST['clientuserID'];
$dateandtime = date('Y-m-d H:i:s', strtotime($_POST['dateandtime']));
$goal = $_POST['goal'];
$type = $_POST['type'];
$amount = $_POST['amount'];
$drinkConsumed = $_POST['drinkConsumed'];
$client_id = $_POST['client_id'];
$dietitian_id = $_POST['dietitian_id'];
$dietitianuserID = $_POST['dietitianuserID'];
$date = $_POST['date'];

$sql = "SELECT drinkConsumed, goal , amount FROM watertracker WHERE client_id='$client_id' AND dateandtime = '$dateandtime'";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) == 0) {
    $sql = "INSERT INTO watertracker (drinkConsumed, goal, clientuserID, dateandtime, type, amount, client_id, dietitian_id, dietitianuserID) VALUES ('$drinkConsumed', '$goal', '$clientuserID', '$dateandtime', '$type', '$amount', '$client_id', '$dietitian_id', '$dietitianuserID')";

    if (mysqli_query($conn, $sql)) {
        echo "inserted_water_goal";
    } else {
        echo "error_in_insertion";
    }
} else {
    while ($row = mysqli_fetch_assoc($result)) {
        $amount = $row['amount'];
        $oldGoal = $row['goal'];
        echo "$amount";
        echo "$oldGoal";
    }
    
    $newGoal = $goal;
    $Totalamount = $amount + $drinkConsumed;
    echo "$Totalamount";

    $sql = "UPDATE watertracker SET amount='$Totalamount' WHERE client_id='$client_id' AND DATE(dateandtime) LIKE '%$date%'";
    
    if (mysqli_query($conn, $sql)) {
        echo "updated_water_goal";
    } else {
        echo "error_in_updation";
    }
}

$conn->close();
?>
