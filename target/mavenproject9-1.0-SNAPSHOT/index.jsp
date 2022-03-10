<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="STYLES/index_style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/4d79e072db.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <script>


        </script>

        <div class="login-box">
          <h2>Login</h2>

          <form action="/mavenproject9/ServletController" method="post">

            <div class="user-box">
              <input type="text" name="user" required="">
              <label>Username</label>
            </div>
            <div class="user-box">
              <input type="password" name="pass" required="">
              <label>Password</label>
            </div>


            <!-- <button type="submit" name="login" class="nondecorated"> -->
            <!-- <button type="button" class="btn btn-outline-success">Success</button>
            <button  type="submit" name="login">Login</button>
            <button  type="submit" name="register">Register</button> -->

            <button class="btn btn-outline-success" type="submit"name="btt" value="login" >Login</button>
            <button class="btn btn-outline-light" type="submit"  name="btt" value="register">Register</button>
            
          </form>

          <!-- <form action="/mavenproject9/ServletController" method="post">
            <div class="user-box">
              <input type="text" name="user" required="">
              <label>Username</label>
            </div>
            <div class="user-box">
              <input type="password" name="pass" required="">
              <label>Password</label>
            </div>

            <a href="http://localhost:8080/mavenproject9/ServletController?">
              <span></span>
              <span></span>
              <span></span>
              <span></span>
              Submit
            </a>

          </form>
          <form action="/mavenproject9/ServletController" method="post">
            <button postData="postdatabtt">POST DATA</button>
          </form> -->

        </div>
    </body>
</html>
