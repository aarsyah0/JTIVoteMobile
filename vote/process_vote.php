<?php
require_once("db_connect.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $election_id = $_POST['e_id'];
    $candidate_id = $_POST['c_id'];
    $voter_id = $_POST['v_id'];

    // Cek apakah pengguna sudah memberikan suara
    $checkIfVoteCasted = mysqli_query($db, "SELECT * FROM votings WHERE voters_id = '$voter_id' AND election_id = '$election_id'");
    $isVoteCasted = mysqli_num_rows($checkIfVoteCasted);

    if ($isVoteCasted > 0) {
        // Pengguna sudah memberikan suara
        echo "AlreadyVoted";
    } else {
        // Pengguna belum memberikan suara, lakukan vote
        $voteDate = date("Y-m-d");
        $voteTime = date("H:i:s");

        $insertVote = mysqli_query($db, "INSERT INTO votings (election_id, voters_id, candidate_id, vote_date, vote_time) VALUES ('$election_id', '$voter_id', '$candidate_id', '$voteDate', '$voteTime')");

        if ($insertVote) {
            // Suara berhasil
            echo "Success";
        } else {
            // Suara gagal
            echo "Failed";
        }
    }
} else {
    // Metode HTTP tidak didukung
    echo "InvalidMethod";
}
?>
