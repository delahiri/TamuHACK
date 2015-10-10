<?php
/**
 * Created by PhpStorm.
 * User: RatneshThakur
 * Date: 10/9/2015
 * Time: 6:58 PM
 */
$PageTitle="Home";
include_once("header.php");

require '../resources/config.php';
if(is_null($GLOBALS['conn']) == true)
{
    $conn = getConnection();
}
?>

<?php

$userid = $_POST["login"];

$sql = "SELECT userid,name,email,current_prob FROM user where userid='".$userid."'";

$result = mysqli_query($conn,$sql);

$username = "default";

while($row = $result->fetch_assoc())
{

    $username = $row["name"];

}



$sql = "SELECT id,name FROM problem_type";

$result = mysqli_query($conn,$sql);

if(!$result || (mysqli_num_rows($result) == 0))
{
    echo "Something went wrong. We are fixing it asap.";
}
else
{
    echo "<h2> Hi ".$username."</h2>";

    echo "<h2> Problem Types</h2>";
    echo "<table class='table table-striped table borderless table-condensed' width='100'>";
    $count = 1;
    while($row = $result->fetch_assoc())
    {

        echo "<tr>";
        echo "<td> Problem involving </td>";
        echo "<td>".$row["name"]."</td>";
        $findPurl = "FindProblemAndPartner.php?problemtypeid=".$row["id"]."&userid=".$userid;
        echo "<td><div style='float:right;'> <a href='".$findPurl."' class='btn btn-primary'> Give it a shot! </a> </div> </td>";
        echo "</tr>";
        $count++;

    }
    echo "</table>";
}

?>


<?php
include_once("footer.php");
mysqli_close($conn);
?>
