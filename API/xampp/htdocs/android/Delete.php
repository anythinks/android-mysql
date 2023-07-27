<?php
include 'Connect.php';
// array for JSON response
$response = array();
if (isset($_POST['kirim'])) {
    $kode = $_POST['kode'];
    $sql = "delete from karyawan where kode = '$kode'";
    mysqli_query($db, $sql);
    $response['hasil'][] = 'Sukses';
} else {
    $response['hasil'][] = 'Gagal';
}
echo json_encode($response);
?>