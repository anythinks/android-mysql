<?php
include 'Connect.php';
// array for JSON response
$response = array();
if (isset($_POST['kirim'])) {
    $kode = $_POST['kode'];
    $nama = $_POST['nama'];
    $alamat = $_POST['alamat'];
    $telp = $_POST['telp'];
    $tgl = $_POST['tgl'];
    $kota = $_POST['kota'];
    $kabupaten = $_POST['kabupaten'];
    $kecamatan = $_POST['kecamatan'];
    $kelurahan = $_POST['kelurahan'];
    $sql = "insert into karyawan values('$kode','$nama','$alamat','$telp','$tgl','$kota','$kabupaten','$kecamatan','$kelurahan')";
    mysqli_query($db, $sql);
    $response['hasil'][] = 'Sukses';
} else {
    $response['hasil'][] = 'Gagal';
}
echo json_encode($response);
?>