<?php

require "connect.php";

$conn = mysqli_connect($server, $username, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$referralCode = $_POST['referralCode'];

$sql = "SELECT * FROM referral_table WHERE referralCode = '$referralCode'";

$result = mysqli_query($conn, $sql) or die("Error in Selecting " . mysqli_error($conn));

if (mysqli_num_rows($result) == 0) {
    $response = [
        'status' => 'not_found'
    ];
} else {
    $rows = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $rows[] = $row;
    }
    $response = [
        'status' => 'found',
        'data' => $rows
    ];
}
echo json_encode($response);

?>
