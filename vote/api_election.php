<?php
include 'koneksi.php';

$sql = "SELECT election_topic FROM elections WHERE status = 'Active'";
$result = $koneksi->query($sql);

if ($result->num_rows > 0) {
    $elections = array();
    while($row = $result->fetch_assoc()) {
        $elections[] = array("election_topic" => $row["election_topic"]);
    }

    header('Content-Type: application/json');
    echo json_encode($elections);
} else {
    echo "0 results";
}

$koneksi->close();
?>
