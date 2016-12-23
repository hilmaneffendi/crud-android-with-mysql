<?php 
	$id = $_GET['id'];
	
	require_once('dbConnect.php');
	
	$sql = "DELETE FROM tb_mahasiswa WHERE id=$id;";
	
	if(mysqli_query($con,$sql)){
		echo 'Data Berhasil di Hapus!';
	}else{
		echo 'Gagal Menghapus Data..';
	}
	
	mysqli_close($con);
