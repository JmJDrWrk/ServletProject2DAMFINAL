<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="STYLES/index_style.css" rel="stylesheet" type="text/css"/>
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
              <a type="submit" name="login">      
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Submit
              </a>
            </button>

            
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
