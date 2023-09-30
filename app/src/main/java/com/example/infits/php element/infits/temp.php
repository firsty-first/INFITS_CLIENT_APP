<?php
require "connect.php";

$error = false;
if (empty($_POST["userID"]) || empty($_POST["password"])) {
    $error = true;
}

$stat = array("Status");
$cases = array("Failure", "Success");
$updt = array();
$products = array();

if ($error) {
    $updt = array("Status" => $cases[0]);
    array_push($products, $updt);
    echo json_encode($products, JSON_UNESCAPED_SLASHES); // Add JSON_UNESCAPED_SLASHES flag
} else {
    $updt = array("Status" => $cases[1]);

    $user_name = $_POST["userID"];
    $user_pass = $_POST["password"];

    $sql = "SELECT * FROM client WHERE clientuserID=? AND password=?";
    $stmt = $conn->stmt_init();
    $stmt->prepare($sql);
    $stmt->bind_param("ss", $user_name, $user_pass);
    $stmt->execute();
    $stmt->bind_result($clientID, $clientuserID, $password, $name, $location, $email, $mobile, $plan, $profilePhoto, $p_p, $dietitian_id, $dietitianuserID, $gender, $age, $verification, $verification_code, $height, $weight, $last_seen);
    $result = $stmt->get_result();
    $resultArray = $result->fetch_assoc();

    if (mysqli_num_rows($result) > 0) {
        $imageName = "$user_name.jpg || $user_name.jpeg || $user_name.png";
        $imageRemoteUrl = 'https://infits.in/androidApi/upload/' . $imageName;

        // Check if the remote image exists
        $headers = get_headers($imageRemoteUrl);
        if (strpos($headers[0], '200') !== false) {
            // Remote image exists, retrieve its content
            $data = file_get_contents($imageRemoteUrl);
        } else {
            // If the remote image does not exist, set the default URL
            $defaultImageUrl = 'https://infits.in/androidApi/upload/default.jpg';
            $data = file_get_contents($defaultImageUrl);
        }

        $temp = array();
        $temp['client_id'] = $resultArray['client_id'];
        $temp['clientuserID'] = $resultArray['clientuserID'];
        $temp['dietitian_id'] = $resultArray['dietitian_id'];
        $temp['dietitianuserID'] = $resultArray['dietitianuserID'];
        $temp['name'] = $resultArray['name'];
        $temp['mobile'] = $resultArray['mobile'];
        $temp['password'] = $resultArray['password'];
        $temp['email'] = $resultArray['email'];
        $temp['location'] = $resultArray['location'];
        $temp['age'] = $resultArray['age'];
        $temp['gender'] = $resultArray['gender'];
        $temp['plan'] = $resultArray['plan'];
        $temp['verification'] = $resultArray['verification'];
        $temp['height'] = $resultArray['height'];
        $temp['weight'] = $resultArray['weight'];
        $temp['p_p'] = $resultArray['p_p'];

        // Fix the profilePhoto URL and remove the backslashes
        $temp['profilePhoto'] = $resultArray['profilePhoto'];

        array_push($products, $updt);
        array_push($products, $temp);

        echo json_encode($products, JSON_UNESCAPED_SLASHES); // Add JSON_UNESCAPED_SLASHES flag
    } else {
        echo "failure";
    }
}
?>
