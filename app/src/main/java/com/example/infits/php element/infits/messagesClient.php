<?php 
require "connect.php";
// Create connection
$conn=mysqli_connect($server,$username,$password,$database);

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

	$dietitianuserID = $_POST['duserID'];
    $clientuserID = $_POST['cuserID'];
	
$stmnt = $conn -> prepare("SELECT * FROM `messages` WHERE dietitianuserID=? and clientuserID=? ORDER BY time ASC");

$stmnt->bind_param("ss",$dietitianuserID,$clientuserID);
$stmnt->execute();
//$stmnt-> bind_result($dietitianuserID,$clientuserID,$message,$messageBy,$time,$status,$type,$filename);
$stmnt-> bind_result($dietitianuserID,$clientuserID,$message,$messageBy,$time);



	$products = array();
	while($stmnt->fetch())
	{
	  	$temp = array();
	  	$temp['dietitianuserID']= $dietitianuserID;
	  	$temp['clientuserID']= $clientuserID;
		$temp['message']= $message;
		$temp['messageBy']= $messageBy;
		$temp['time']=$time;
		//$temp['type'] = $type;
		//$temp['filename'] = $filename;
	  
	array_push($products,$temp);
	}
	
if(count($products)>-1){
echo json_encode($products);
}

else {
echo "failure";
}

?>
