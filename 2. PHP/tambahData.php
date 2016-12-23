<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$npm = $_POST['npm'];
		$nm = $_POST['nama_mahasiswa'];
		$jrs = $_POST['jurusan'];
		
		//Creating an sql query
		$sql = "INSERT INTO tb_mahasiswa (npm,nama_mahasiswa,jurusan) VALUES ('$npm','$nm','$jrs')";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Data Berhasil Tesimpan!';
		}else{
			echo 'Gagal Menyimpan Data';
		}
		
		//Closing the database 
		mysqli_close($con);
	}