<?php
/**
 * Created by PhpStorm.
 * User: RatneshThakur
 * Date: 10/9/2015
 * Time: 6:58 PM
 */
$PageTitle="Home";
//include_once("header.php");

require '../resources/config.php';
if(is_null($GLOBALS['conn']) == true)
{
    $conn = getConnection();
}
$code = $_POST['code'];
$problemid = $_POST['problemid'];
$userid = $_SESSION['userid'];
$sql = "INSERT INTO user_solutions(userid,problemid,solution) VALUES ('$userid','$problemid','$code')";
$solutionid = mysqli_query($conn,$sql);



?>