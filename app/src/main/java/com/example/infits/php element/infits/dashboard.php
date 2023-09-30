<?php 
require "connect.php";
// Create connection
$conn=mysqli_connect($server,$username,$password,$database);
$conn2=mysqli_connect($server,$username,$password,$database);
$conn3=mysqli_connect($server,$username,$password,$database);
$connfic=mysqli_connect($server,$username,$password,$database);

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

	$userID = $_POST['userID'];
	
	$stmnt = $conn -> prepare("SELECT steptracker.goal, SUM(steptracker.steps)
	FROM steptracker
	WHERE steptracker.clientuserID = ? 
	  AND CAST(steptracker.dateandtime AS DATE) = CURRENT_DATE
	GROUP BY steptracker.goal;
	");
	
	$stmnt-> bind_param("s",$userID);
	$stmnt-> execute();
	$stmnt-> bind_result($stepsgoal,$steps);
	

    $stmnt2 = $conn2 -> prepare("SELECT watertracker.goal, SUM(watertracker.amount), watertracker.drinkConsumed
	FROM watertracker
	WHERE watertracker.clientuserID = ? 
		AND CAST(watertracker.dateandtime AS DATE) = CURRENT_DATE
	GROUP BY watertracker.goal, watertracker.drinkConsumed;
	");	

	$stmnt2-> bind_param("s",$userID);
	$stmnt2-> execute();
	$stmnt2-> bind_result($watergoal,$water,$waterTotal);

	$stmnt3 = $conn3 -> prepare("SELECT sleeptracker.goal, SUM(sleeptracker.hrsSlept), SUM(sleeptracker.minsSlept) FROM sleeptracker
					WHERE sleeptracker.clientuserID =? 
					AND cast(sleeptracker.waketime as DATE) =  CURRENT_DATE GROUP BY sleeptracker.goal");
	
	$stmnt3-> bind_param("s",$userID);
	$stmnt3-> execute();
	$stmnt3-> bind_result($sleepgoal,$sleephrs,$sleepmins);

	$stmntfic = $connfic -> prepare("SELECT weighttracker.goal, SUM(weighttracker.weight) FROM weighttracker
    WHERE weighttracker.clientuserID =? 
    AND cast(weighttracker.dateandtime as DATE) =  CURRENT_DATE GROUP BY weighttracker.goal");

	$stmntfic-> bind_param("s",$userID);
	$stmntfic-> execute();
	$stmntfic-> bind_result($weightGoal,$weight);

	$products = array();
	$temp = array();
	

	while($stmnt->fetch()){
	//   $temp = array();
	  
	  $temp['stepsgoal']= $stepsgoal;
	  $temp['steps']= $steps;
	}

	while ($stmnt2->fetch()) {
        $temp['watergoal']= $watergoal;
	  	$temp['water']= $water;
		$temp['waterConsumed'] = $waterTotal;
    }


    while($stmnt3->fetch()){
	
		if ($sleepmins >= 60) {
			$temp['sleepmins'] = $sleepmins - 60;
			$temp['sleephrs'] = $sleephrs + 1;
		}
		else{
			$temp['sleephrs']= $sleephrs;
        	$temp['sleepmins']= $sleepmins;
		}
        $temp['sleepgoal']= $sleepgoal;
        
      }

      while ($stmntfic->fetch()) {
        $temp['weight']= $weight;
	  	$temp['weightgoal']= $weightGoal;
      }

	  array_push($products,$temp);

if(count($products)>0){
echo json_encode($products);
}

else {
echo "failure";
}
?>
