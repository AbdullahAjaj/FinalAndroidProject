<?php
$host = 'localhost';
$username = 'root';
$pwd = '';
$dp = 'hotel';
$con = mysqli_connect($host,$username,$pwd,$dp) or die ('Uneble to conect');
if($con->connect_error)
{
    echo "Filed to connect";
}
$sql = "SELECT * From room";
$result = mysqli_query($con,$sql);
if($result){
    while($row=mysqli_fetch_array($result))
    {
        $flag[] = $row;
    }
    print(json_encode($flag));

}
mysqli_close($con);
?>