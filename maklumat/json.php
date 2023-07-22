<?php

require_once('conn.php');

$query = "SELECT * FROM maps ORDER BY date_time DESC";
$result = mysqli_query($conn, $query);

//declare array
$output = array();

foreach ($result as $row) {
    array_push($output,$row);
}

// assign to json variable
$json = json_encode($output);

//declare document type to json and output json
header("Content-Type: application/json");
echo $json;

?>