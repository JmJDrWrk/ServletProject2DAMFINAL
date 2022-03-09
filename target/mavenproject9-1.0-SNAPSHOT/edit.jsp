<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8mb4"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Document</title>
    <link href="STYLES/edit_style.css" rel="stylesheet" type="text/css"/>


</head>

    <body>

      <div class='container'>
          
        <div class='panel panel-primary dialog-panel'>
          <div class='panel-heading'>
            <h5>Gpu- Edit Panel</h5>
          </div>
          <div class='panel-body' id="panelbody">
            <form class='form-horizontal' role='form' id="form" action="/mavenproject9/ServletController" method="post">
              <div class='form-group' id="formgroup"> 
                <div class="row">
                    <div class="column">

                    <!-- Modelname -->
                        <label class='control-label col-md-2' for='id_modelname'>Modelname</label>
                        <div class='col-md-3'>
                        <div class='form-group internal input-group'>
                            <input class='form-control datepicker' id='id_modelname'  aria-label="Small" name="modelname" required value="${gpu.modelname}">
                            <span class='input-group-addon'>
                            <i class='glyphicon glyphicon-calendar'></i>
                            </span>
                        </div>
                        </div>

                    <!-- Company -->
                        <label class='control-label col-md-2' for='id_company'>Company</label>
                        <div class='col-md-3'>
                        <div class='form-group internal input-group'>
                            <input class='form-control datepicker' id='id_company' name="company" required value="${gpu.company}">
                            <span class='input-group-addon'>
                            <i class='glyphicon glyphicon-calendar'></i>
                            </span>
                        </div>
                        </div>

                <!-- Year -->
                <label class='control-label col-md-2' for='id_year'>Year</label>
                    <div class='col-md-3'>
                    <div class='form-group internal input-group'>
                        <input class='form-control datepicker' id='id_year' name="year" required value="${gpu.year}">
                        <span class='input-group-addon'>
                        <i class='glyphicon glyphicon-calendar'></i>
                        </span>
                    </div>
                    </div>


                <!-- Memory -->
                <label class='control-label col-md-2' for='id_memory'>Memory</label>
                    <div class='col-md-3'>
                    <div class='form-group internal input-group'>
                        <input class='form-control datepicker' id='id_memory' name="memory" required value="${gpu.memory}">
                        <span class='input-group-addon'>
                        <i class='glyphicon glyphicon-calendar'></i>
                        </span>
                    </div>
                    </div>

                <!-- Power -->
                <label class='control-label col-md-2' for='id_power'>Power</label>
                    <div class='col-md-3'>
                        <div class='form-group internal input-group'>
                        <input class='form-control datepicker' id='id_power' name="power" required value="${gpu.power}" >
                        <span class='input-group-addon'>
                            <i class='glyphicon glyphicon-calendar'></i>
                        </span>
                        </div>
                    </div>
                
               
                
                <form action="/mavenproject9/ServletController" method="post">
                    <!-- BUTTONS -->
                    <!-- <a href="${pageContext.request.contextPath}/ServletController?accion=OneditGpu&event1=cancel">
                        <button id="cancel_bt" type="button" class="btn btn-warning">Cancel</button></a>

                    <a href="${pageContext.request.contextPath}/ServletController?accion=OneditGpu&event1=confirm" type="submit">
                    <button id="confirm_bt" type="submit" class="btn btn-success">Confirm</button></a> -->
                    <button id="cancel_bt" type="submit" class="btn btn-warning" name="btt" value="cancel">Cancel</button>
                    <button id="confirm_bt" type="submit" class="btn btn-success"  name="btt" value="confirm" >Confirm</button>
                </form>


                    </div>
                    
                <div class="column">
                    
                <!-- Socket -->
                <label class='control-label col-md-2 col-md-offset-2' for='id_socket'>Socket</label>
                <div class='col-md-2'>
                <select class='form-control' id='id_socket' name="socket" required value="${gpu.socket}">
                    <option>PCIe</option>
                    <option>PCI</option>
                    <option>AGP</option>
                    <option>ISA</option>
                </select>
                </div>
            

                <!-- Price -->
                <label class='control-label col-md-2' for='id_price'>Price</label>
                    <div class='col-md-3'>
                        <div class='form-group internal input-group'>
                        <input class='form-control datepicker' name="price" id='id_price' required value="${gpu.price}">
                        <span class='input-group-addon'>
                            <i class='glyphicon glyphicon-calendar'></i>
                        </span>
                        </div>
                    </div>
                    

                <!-- Type -->
                <label class='control-label col-md-2 col-md-offset-2'  for='id_type'>Type</label>
                    <div class='col-md-2'>
                    <select class='form-control' id='id_type' name="type" required value="${gpu.type}">
                        <option>Low-Profile</option>
                        <option>Standard</option>
                        <option>Laptop</option>
                    </select>
                    </div>


                
                <!-- ImageURL -->
                <label class='control-label col-md-2' for='id_imageurl'>ImageURL</label>
                    <div class='col-md-3'>
                        <div class='form-group internal input-group'>
                        <input class='form-control datepicker' id='id_imageurl' name="imageurl" required value="${gpu.imageurl}">
                        <span class='input-group-addon'>
                            <i class='glyphicon glyphicon-calendar'></i>
                        </span>
                        </div>
                    </div>
                
                        <!-- src\main\webapp\RESOURCES\Tarjetas Gráficas 【Nvidia y AMD】 ► PcComponentes_files\zotac-gaming-geforce-gtx-1650-oc-4gb-gddr6.jpg -->
                        <!-- <img class="picture" src="RESOURCES/Tarjetas GrÃ¡ficas ãNvidia y AMDã âº PcComponentes_files/1116-gigabyte-geforce-rtx-3070-gaming-oc-lhr-v2-8gb-gddr6.jpg" alt=""> -->
                        <picture>
                        <source srcset="${gpu.imageurl}" type="image/svg+xml">
                        <img required value="${gpu.imageurl}" src="${gpu.imageurl}" class="img-fluid img-thumbnail" alt="The image couldnÂ´t be loaded">
                    </picture>
                    </div>

                    <div class="columns">
       
                    </div>

                  </div>




                <!-- OWN EDITED  -->
               
                </div>
            </form>
            </div>
        </div>
      </div>
    </body>
    
</html>