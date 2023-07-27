<?php include 'Connect.php';

// array for JSON response
$response = array();
$result = mysqli_query($db, "SELECT * FROM karyawan");
while ($row = mysqli_fetch_assoc($result)) {
      $response['hasil'][] = $row;
}
echo json_encode($response);
?>