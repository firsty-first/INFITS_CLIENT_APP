<?php 
require "connect.php";

$error = false;
if (empty($_POST["userID"])) {
    $error = true;
}

$stat = array("Status");
$cases = array("Failure", "Success");
$updt = array();
$products = array();

if ($error) {
    $updt = array("Status" => $cases[0]);
    array_push($products, $updt);
    echo json_encode($products);
} else {
    $updt = array("Status" => $cases[1]);
    $user_name = $_POST["userID"];

    $sql = "SELECT profilePhoto, p_p FROM client WHERE clientuserID=?";

    $stmt = $conn->stmt_init();
    
    $stmt->prepare($sql);
    $stmt->bind_param("s", $user_name);
    $stmt->execute();
    $stmt->bind_result($profilePhoto, $p_p); // Fix the number of variables here (two in this case)
    $result = $stmt->get_result();
    $resultArray = $result->fetch_assoc();
    
    if(mysqli_num_rows($result) > 0) {
        $imageName = "$user_name.jpg";
        $image = 'upload/'.$imageName;
        $type = pathinfo($image, PATHINFO_EXTENSION);
        
        if (file_exists($image) == false) {
            $data = file_get_contents("C:/xampp/htdocs/infits/upload/default.jpg");
        } else {
            $data = file_get_contents($image);
        }
  
        $temp = array();
        $temp['p_p'] = $resultArray['p_p'];
        $temp['profilePhoto'] = base64_encode($data);
        
        array_push($products, $updt);
        array_push($products, $temp);

        echo json_encode($products);
    } else {
        echo "failure";
    }
}
?>
