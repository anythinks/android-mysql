<?php
$servername = "localhost";
$user = "root";
$password = "";
$database = "android";

$connect = mysqli_connect($servername, $user, $password, $database);
if ($connect->connect_error) {
    die("Connect Error");
}
?>