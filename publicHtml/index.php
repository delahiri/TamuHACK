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

$sql = "SELECT id,problem_name FROM problems";

$result = mysqli_query($conn,$sql);

if(!$result || (mysqli_num_rows($result) == 0))
{
    echo "Something went wrong. We are fixing it asap.";
}
else
{
    echo "<h2> Problems </h2>";
    echo "<table class='table table-striped table borderless table-condensed' width='100'>";
    $count = 1;
    while($row = $result->fetch_assoc())
    {

        echo "<tr>";
        echo "<td> Problem no. ".$count."</td>";
        echo "<td>".$row["problem_name"]."</td>";
        $problemurl = "tryProblem.php?id=".$row["id"];
        echo "<td><div style='float:right;'> <a href='".$problemurl."' class='btn btn-primary'> Give it a shot! </a> </div> </td>";

        echo "</tr>";
        $count++;
        //onClick="return confirm('Delete This account?')"
        //echo "contact name : ". $row["contact_name"];
        //echo "<br>";
    }
    echo "</table>";
}

?>


<?php
include_once("footer.php");
mysqli_close($conn);
?>
