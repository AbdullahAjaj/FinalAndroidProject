<?php 

if($_SERVER['REQUEST_METHOD']=='GET'){

  $server_name = "localhost";
	$username = "root";
	$password = "";
	$dbname = "hotel";

    $conn = new mysqli($server_name,$username,$password,$dbname);
    $userName = isset($_GET['userName'])? $_GET['userName'] : "";
    $Sql_Query = "Select * from customer where userName= '$userName' ";
    $res = $conn->query( $Sql_Query);

    if ($res!=false && $res->num_rows > 0){
        $id = $res->fetch_assoc()['roomID'];
       if($id == null)
            die("Error, cannot check out from this room");
  

        $Sql_Query = "UPDATE room set isReserved = '0' , userName='' where userName like '%$userName%';";
        $Sql_Query .= "UPDATE customer set roomID = '',totalPrice=''  where userName='$userName' ;";
        
            $res = $conn->multi_query($Sql_Query);
        
            
        if($res == true)
            echo "Check Out successfully";
        else
            echo "Erorr occured while checking in";
        
        $conn->close(); 

    }

   
}
else {
    echo "request method should be GET ";
}
?>