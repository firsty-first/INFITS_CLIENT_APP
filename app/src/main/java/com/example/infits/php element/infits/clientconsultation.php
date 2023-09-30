<?php

require "connect.php";
// Create connection
$conn = mysqli_connect($server, $username, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$question = isset($_POST['question']) ? json_decode($_POST['question'], TRUE) : [];
$answer = isset($_POST['answer']) ? json_decode($_POST['answer'], TRUE) : [];

$userID = $_POST['userID'];
$name = $_POST['name'];

//$tablename = $_POST['tablename'];


if (!empty($question) && !empty($answer)) {
    $sql = "select * from client_consultation where clientuserID='$userID'";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) == 0) {
        for ($i = 0; $i < count($question); $i++) {
            $sql = "insert into client_consultation VALUES ('$userID','$name','$question[$i]','$answer[$i]');";

            try {
                if ($conn->query($sql)) {
                    echo "success";
                }
            } catch (mysqli_sql_exception $th) {
                echo $th;
            }
        }
    } else {
        for ($i = 0; $i < count($question); $i++) {
            $sql = "update client_consultation set answers = '$answer[$i]' where question = '$question[$i]' and clientuserID = '$userID'";

            try {
                if ($conn->query($sql)) {
                    echo "success";
                }
            } catch (mysqli_sql_exception $th) {
                echo $th;
            }
        }
    }
} else {
    echo "Question and answer data is empty or invalid.";
}
?>
