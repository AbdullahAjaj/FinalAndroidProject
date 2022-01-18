<?php 

if($_SERVER['REQUEST_METHOD']=='GET'){

  $server_name = "localhost";
	$username = "root";
	$password = "";
	$dbname = "hotel";

    $conn = new mysqli($server_name,$username,$password,$dbname);
    $roomID = isset($_GET['roomID'])? $_GET['roomID'] : "";
    $totalPrice= isset($_GET['totalPrice'])? $_GET['totalPrice'] : "";
    $userName = isset($_GET['userName'])? $_GET['userName'] : "";
    $Sql_Query = "select * from room where roomID = '$roomID';";
    $res = $conn->query($Sql_Query);
    if($res!=false && $res-> num_rows > 0){
        $Sql_Query = "UPDATE room set isReserved = '1' , userName=' $userName' where roomID = '$roomID';";
        $Sql_Query .= " UPDATE customer set roomID = '$roomID'  where userName='$userName' ;";
        $Sql_Query .= " UPDATE customer set totalPrice= '$totalPrice'  where userName='$userName' ;";

        
        $res = $conn->multi_query($Sql_Query);
       
           
       if($res == true)
           echo "Check in successfully";
       else
           echo "Erorr occured while checking in";
       
       $conn->close();
    }
    else 
        echo "This room id doesn't exist";
 
}
else {
    echo "request method should be PUT ";
}
?>