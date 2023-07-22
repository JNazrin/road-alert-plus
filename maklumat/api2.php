<?php

require_once('conn.php');

if (strlen($_POST['name']) < 1  || strlen($_POST['type']) < 1 || strlen($_POST['location']) < 1 || strlen($_POST['description']) < 1 || strlen($_POST['lat']) < 1 || strlen($_POST['lng']) < 1) {
    die("Error plese fill in the form");
}


$name = $_POST['name'];
$type = $_POST['type'];
$location = $_POST['location'];
$desc = $_POST['description'];
$lat = $_POST['lat'];
$lng = $_POST['lng'];

$sql = "INSERT INTO maps (name, type, location, description, lat, lng) VALUES ('$name','$type', '$location', '$desc','$lat','$lng')";


if (mysqli_query($conn, $sql)) {
    echo "Data inserted successfully.";
} 
else {
    echo mysqli_error($conn);
}

?>