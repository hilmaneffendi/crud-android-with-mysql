<?php 
	$id = $_GET['id'];
	
	require_once('dbConnect.php');
	
	$sql = "SELECT * FROM tb_mahasiswa WHERE id=$id";
	$r = mysqli_query($con,$sql);
	
	$result = array();
	
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id"=>$row['id'],
			"npm"=>$row['npm'],
			"nama_mahasiswa"=>$row['nama_mahasiswa'],
			"jurusan"=>$row['jurusan']
		));

	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);