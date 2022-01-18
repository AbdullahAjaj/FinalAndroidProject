<?php 

if($_SERVER['REQUEST_METHOD']=='GET'){

  $server_name = "localhost";
	$username = "root";
	$password = "";
	$dbname = "hotel";

    $conn = new mysqli($server_name,$username,$password,$dbname);
    $roomID = isset($_GET['roomID'])? $_GET['roomID'] : "";
    $userName = isset($_GET['userName'])? $_GET['userName'] : "";
    $Sql_Query = "Select * from customer where roomID = '$roomID'";
    $res = $conn->query( $Sql_Query);

    if ($res!=false && $res-> num_rows > 0)
     die("Error, cannot check out from this room");
    $Sql_Query = "UPDATE room set isReserved = '0' , userName='' where roomID = '$roomID';";
    $Sql_Query .= "UPDATE customer set roomID = ''  where userName='$userName' ;";

    $res = $conn->multi_query($Sql_Query);

	
if($res == true)
    echo "Check out successfully";
else
    echo "Erorr occured while checking in";

$conn->close(); 
}
else {
    echo "request method should be GET ";
}
?>