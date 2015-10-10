<?php
$code = $_POST['code'];
$conct = mysqli_connect("localhost","root","",tamuhack)	 or die("Could not connect to database!");
$result1 = mysqli_query($conct,"INSERT INTO awai VALUES ('$code')");
?>
