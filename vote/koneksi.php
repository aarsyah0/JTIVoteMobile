<?php
$host = "jti-vote.tifint.myhost.id"; 
$username = "tifintmy_jtivote"; 
$password = "@JTIpolije2023"; 
$database = "tifintmy_jtivote"; 

$koneksi = mysqli_connect($host, $username, $password, $database);

if (!$koneksi) {
    die("Connection failed: " . mysqli_connect_error());
}
?>
