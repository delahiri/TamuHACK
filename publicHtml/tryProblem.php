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

$problemid = $_GET["id"];

echo "<h2>".$problemid."</h2>";

$sql = "select solution_template from problems where id='".$problemid."'";
$result = mysqli_query($conn,$sql);

if(!$result || (mysqli_num_rows($result) == 0))
{
    echo "Something went wrong. We are fixing it asap.";
}
else
{
    $row = $result->fetch_assoc();
    $solution_template = $row['solution_template'];
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Editor</title>
    <style type="text/css" media="screen">

        .scrollmargin {
            height: 80px;
            text-align: center;
        }
    </style>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
    <script>
        function func(problemid){
            var string = editor.getValue();

            var input = document.getElementById("code");
            input.value = string;
            $.ajax({
                url: 'insert_into_database.php',
                type: 'POST', // performing a POST request
                data : {
                    code : string, // will be accessible in $_POST['data1']
                    problemid: problemid
                },
                success: function(data)
                {
                    alert('hi');
                    window.console.log('Successful');
                }
            });return false;
            //submit_function();
        }
    </script>
</head>
<body>
<div class="jumbotron">
    <div class="row">
        <div class="col-lg-6 col-md-8 col-sm-8">
            <pre id="editor3" style="width: 100%;"> <?php echo $solution_template ?></pre>
            <div class="scrollmargin"></div>
            <button class="btn btn-success" onclick="func(<?php echo $problemid?>);" type="button">Submit</button>
            <div id="hidden_form">
                <form name="form" id="form_hidden" action="">
                    <input type="hidden" name="code" id="code" value=""/>
                </form>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4">
            <h2>Here is the problem</h2>
        </div>
    </div>


</div>


<!-- load ace -->
<script src="src/ace.js"></script>
<script>

    var editor = ace.edit("editor3");
    editor.setTheme("ace/theme/twilight");
    editor.getSession().setMode("ace/mode/java");
    editor.setOptions({
        autoScrollEditorIntoView: true,
        maxLines: 20
    });
    editor.renderer.setScrollMargin(10, 10, 10, 10);
</script>

<script src="./show_own_source.js"></script>

</body>
</html>
