<?php 

if($_SERVER['REQUEST_METHOD'] == 'GET'){
        $server_name = "localhost";
		$username = "root";
		$password = "";
		$dbname = "hotel";

    $conn = new mysqli($server_name,$username,$password,$dbname);

    if($conn->connect_error){
        die("Connection failed: " . $conn->connect_error);
    }
    $roomID = isset($_GET['roomID'])? $_GET['roomID'] : "";

    $sql = "select isReserved from room where roomID = '$roomID'";
    $result = $conn->query($sql);

    if( $result!=false&&$result->num_rows>0){
        $res = $result->fetch_assoc();
        if($res['isReserved'] == 1)
            echo "This room is reserved";
   
    }
    else
        echo"This room id doesn't exist";



    $conn->close();


}











?>