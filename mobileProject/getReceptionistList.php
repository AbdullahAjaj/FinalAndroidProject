<?php 

    define('server_name','localhost');
    define('username','root');
    define('password','');
    define('dbname','hotel');

    $conn = new mysqli(server_name, username, password, dbname);

    if($conn->connect_error){
        die("Connection failed: " . $conn->connect_error);
    }

    $stmt = $conn->prepare("select roomID, capacity, priceByDay , userName from room;");

    $stmt->execute();

    $stmt->bind_result($roomID, $capacity, $priceByDay , $userName);

    $rooms = array();

    while($stmt->fetch()){
        $temp = array();
        $temp['roomID'] = $roomID;
        $temp['capacity'] = $capacity; 
        $temp['priceByDay'] = $priceByDay; 
        $temp['userName']=$userName;
        array_push($rooms, $temp);
    }

    echo json_encode($rooms); 

?>