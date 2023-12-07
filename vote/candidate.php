<?php

// Database configuration
include 'koneksi.php';

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Fetch candidates data from the database
$query = "SELECT * FROM candidate_details";
$result = $conn->query($query);

// Check if there are results
if ($result->num_rows > 0) {
    // Initialize an array to store candidates data
    $candidates = array();

    // Fetch candidates data
    while ($row = $result->fetch_assoc()) {
        $candidates[] = $row;
    }

    // Convert candidates data to JSON format
    $jsonResponse = json_encode($candidates);

    // Set headers to indicate JSON content
    header('Content-Type: application/json');

    // Output the JSON response
    echo $jsonResponse;
} else {
    // If no candidates found, return an empty JSON array
    echo "[]";
}

// Close the database connection
$conn->close();

?>
