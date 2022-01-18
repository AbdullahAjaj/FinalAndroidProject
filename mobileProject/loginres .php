<?php 

if($_SERVER['REQUEST_METHOD']=='GET'){

  $server_name = "localhost";
	$username = "root";
	$password = "";
	$dbname = "hotel";

    $conn = new mysqli($server_name,$username,$password,$dbname);
 
 $rn= isset($_GET['rn'])? $_GET['rn'] : "";
 $rp= isset($_GET['rp'])? $_GET['rp'] : "";

 
 $Sql_Query = "select * from receptionist where receptionistname = '$rn' and receptionistpass = '$rp' ";
 
 $res = $conn->query($Sql_Query);
 if($rn == "" || $rp =="")
	die("Empty username or password");
	
 if(
	 $res!=false && $res-> num_rows > 0){
 echo "Log in sucessfully";
 }else{
 echo "Invalid Username or Password Please Try Again";
 }

$conn->close(); 
}
?>