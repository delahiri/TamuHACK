<?php
session_start();
require '../resources/config.php';
if(is_null($GLOBALS['conn']) == true)
{
    $conn = getConnection();
}





?>

<?php
$name = $_POST["login"];
$password = $_POST["password"];


$sql = "SELECT userid,password from user where name='".$name."'";
$result = mysqli_query($conn,$sql);
$validlogin = false;
$result = mysqli_query($conn,$sql);


if(!$result || (mysqli_num_rows($result) == 0))
{
	echo $conn->error;
	echo "Something went wrong. We are fixing it asap.";
}
else
{
	while($row = $result->fetch_assoc())
	{
		$userid = $row["userid"];

		$pass = $row["password"];

	}

	if($password == $pass)
	{
		echo "password correct";
		$_SESSION["userid"] = $userid;
		$_SESSION["favColor"] = "blue";
		header('Location: index.php');
		exit;

	}

	header('Location: loginPage.php');
	exit;

}



?>