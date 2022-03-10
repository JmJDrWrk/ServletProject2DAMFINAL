<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="STYLES/console.css">
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/4d79e072db.js" crossorigin="anonymous"></script> -->
</head>
<style>
    #console {
        font-size: 20px;
        width: 60%;
        margin-left: 20%;
        margin-right: 20%;
    }
</style>

<body>
    <form action="/mavenproject9/ServletController" method="post">


        <textarea name="ConsoleContent" id="console" value="textarea" cols="30" rows="10">${final_output}</textarea>

        <p>SELECT * FROM gpus#id,modelname,company,year,memory,power,socket,price,type,imageurl,idcomp</p>

    <button id="cancel_bt" type="submit" class="btn btn-warning"   name="btt" value="return" >Return</button>
    <button id="confirm_bt" type="submit" class="btn btn-success"  name="btt" value="sendconsole" >QUERY</button>


</form>


</body>
</html>