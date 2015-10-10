<?php
require '../resources/config.php';
if(is_null($GLOBALS['conn']) == true)
{
    $conn = getConnection();
}





?>

<?php
$userid = $_POST["login"];
$password = $_POST["password"];

$sql = "SELECT password from user where userid='".$userid."'";
$result = mysqli_query($conn,$sql);
$validlogin = false;
$result = mysqli_query($conn,$sql);
while($row = $result->fetch_assoc())
{

    $pass = $row["password"];

}

if($password == $pass)
{
	$validlogin = true;
}

if(!$validlogin)
{
	header('Location: loginPage.php');
	exit;
}
else
{
	session_start();
	$_SESSION["userid"] = $userid;
	header('Location: index.php');
	exit;
	
}



?>