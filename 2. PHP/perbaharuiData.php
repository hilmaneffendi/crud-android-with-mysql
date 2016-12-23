<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id = $_POST['id'];
		$npm = $_POST['npm'];
		$nama = $_POST['nama_mahasiswa'];
		$jurusan = $_POST['jurusan'];
		
		require_once('dbConnect.php');
		
		$sql = "UPDATE tb_mahasiswa SET npm = '$npm', nama_mahasiswa = '$nama', jurusan = '$jurusan' WHERE id = $id;";
		
		if(mysqli_query($con,$sql)){
			echo 'Data Berhasil di Perbaharui!';
		}else{
			echo 'Gagal Memperbaharui Data';
		}
		
		mysqli_close($con);
	}