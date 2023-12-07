<?php
include 'koneksi.php';

$username = $_GET['username'];
$password = $_GET['password'];

$cek= "SELECT * FROM users WHERE username = '$username' AND password = '$password'";

$msql = mysqli_query($koneksi, $cek);

$result = mysqli_num_rows($msql);

if (!empty($username) && !empty($password)) {
    if($result == 0) {
        echo "0";
    } else {
        $row = mysqli_fetch_assoc($msql);
        $id = $row['id'];
      
        $response = array('id' => $id);
        echo json_encode($response);
    }
} else {
    echo "data harus diisi";
}
?>