<?php
require "connect.php";

// Assuming you are receiving clientID and dateandtime via POST
$clientID = $_POST['clientID'];
$dateandtime = $_POST['dateandtime'];

// Ensure that dateandtime is in a valid format
$dateTime = date_create_from_format('Y-m-d H:i:s', $dateandtime);

if (!$dateTime) {
    // Handle invalid dateandtime format
    echo json_encode(['error' => 'Invalid dateandtime format']);
    exit;
}

// Prepare and execute the SQL query using a prepared statement
$sql = "SELECT * FROM in_app_notifications WHERE clientuserID = ? AND DATE(date) = ?";
$stmt = mysqli_prepare($conn, $sql);
mysqli_stmt_bind_param($stmt, 'ss', $clientID, $dateTime->format('Y-m-d'));

if (mysqli_stmt_execute($stmt)) {
    $result = mysqli_stmt_get_result($stmt);
    
    $full = array();

    while ($row = mysqli_fetch_assoc($result)) {
        $emparray = array();
        $emparray['date'] = date("d-m-Y", strtotime($row['date']));
        $emparray['time'] = date("g:i A", strtotime($row['date']));
        $emparray['type'] = $row['type'];
        $emparray['text'] = $row['text'];
        array_push($full, $emparray);
    }

    echo json_encode(['inApp' => $full]);
} else {
    // Handle SQL query execution error
    echo json_encode(['error' => 'Error executing SQL query']);
}

mysqli_close($conn);
?>
