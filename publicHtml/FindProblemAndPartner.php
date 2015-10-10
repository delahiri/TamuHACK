<?php
/**
 * Created by PhpStorm.
 * User: RatneshThakur
 * Date: 10/9/2015
 * Time: 10:29 PM
 */
$PageTitle="Finding Problem";
include_once("header.php");

require '../resources/config.php';
if(is_null($GLOBALS['conn']) == true)
{
    $conn = getConnection();
}
?>

<h3> Found a nice problem for you and a person who is solving the same problem</h3>

<?php

//echo "id receved ".$_GET["id"];
// here problem id needs to be a randomly generated no.


$problemid = 1;   //randomly generated. through a query.
$userid = $_SESSION["userid"];
$problemtypeid = $_GET["problemtypeid"];
$sql = "SELECT id,problem_name,statement,testcases FROM problems where problem_type='".$problemtypeid."'";

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
        $problemid = $row["id"];
        echo "<div>";
        echo "<b> Problem : </b>";
        echo "<span>".$row["problem_name"]."</span>";
        echo "</br>";
        echo "<span>".$row["statement"]."</span> <br>";
        echo "</div>";

    }

    $sql = "UPDATE user SET current_prob ='".$problemid."' where userid='".$_GET["userid"]."'";
    $result = mysqli_query($conn,$sql);
}

echo "<h3> User Matched is  </h3>";



$sql = "Select name from user where current_prob = (select current_prob from user where userid = ".$_GET["userid"].") and userid <> ".$_GET["userid"];
$result = mysqli_query($conn,$sql);

if(!$result || (mysqli_num_rows($result) == 0))
{
    echo $conn->error;
    echo " We could not find any user. Do you want to solve this problem ?";
}
else
{
    $count = 0;
    while($row = $result->fetch_assoc())
    {
        $count++;
        echo "<div>";
        echo "<b> User </b>";
        echo "<span>".$row["name"]."</span>";
        echo "</div>";
        echo "<br> <br>";
        $problemhref = 'tryProblem.php?id='.$problemid;
        echo "<a class='btn btn-success' href=".$problemhref."> Continue to Problem </a>";
        break;
    }
}

?>



<?php
echo '<script type="text/javascript">
  setTimeout(function () { location.reload(true); }, 5000);
</script>';
include_once("footer.php");
$conn->close();
?>
