<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div class="col-md-3">
    <a  href="${pageContext.request.contextPath}/ServletController?accion=AddGpu">Add</a>
</div>
<section id="clientes">
    
    <!--
    <h1>${usuarios.toString()}</h1>
    <h1>${usuarios.size()}</h1>
    <h1>${usuarios.get(0).toString()}</h1>
    -->
    
    <style type="text/css">
        #cardtitle {
            font-size: 12px;
            
        }

        .card-text {

            font-size: 16px;
        }



	</style>

    <script type = "text/javascript">  

        //CALL THIS ON WINDOW LOAD
        window.onload = function() {
            console.log("Function Called On Load")

            for (let i = 0; i < 100; i++) {
                try {//Found element by id
                    var x = document.getElementById("hiddeableElement_"+i);
                    //Turn to display none all
                    x.style.display = "none";
                }
                catch (e) { //If element couldn´t be found
                    console.log("\t[ERROR] FindById(" + i + ")");
                }
            }

        };

        function myfunction(that){   
            //alert("?hide?");
            //document.getElementById("hiddeableElement").style.display = "none";
            //document.getElementById("company").value = "0Company0";
            //alert("that " + that + " --tagName " + that.tagName + " --OuterHTML " + that.outerHTML);
        
            var id_x = that.outerHTML.split('tag="')[1].split('">')[0];
            //alert('id_x = ' + id_x);

            var x = document.getElementById("hiddeableElement_"+id_x);
            //x.style.display = "inline";
            //alert("jaime1");
            //x.style.visibility = "visible";
            //x.style.display = "block";
            console.log("x.style.visibility = " + x.style.visibility);
            console.log("x.style.display = " + x.style.display);

            if (x.style.display === "none") {
                x.style.display = "inline";
            } else {
                x.style.display = "none";
            }

            console.log("x.style.visibility = " + x.style.visibility);
            console.log("x.style.display = " + x.style.display);
            //alert("jaime2")
            }  

        //SCRIPT FOR HIDDING ELEMENT NOT IN SEARCHABLE
        function findString(){
            var searchbox = document.getElementById("searchbox");
            console.log("Looking for " + searchbox.value);

            var key = searchbox.value;

            
            for (let i = 1; i < 100; i++) {
                try {//Found element by id
                    var x = document.getElementById("eachcard_"+i);
                    //get name about
                    var html = x.innerHTML;
                    var name = html.split("<h5")[1].split("</h5>")[0];
                    var another = document.getElementById("cardtitle_" + i);
                    var realmodelname =  another.textContent.trim() ;
                    //console.log("\t" + x.innerHTML + "\nEHHHH\n" + name + "\nANOTHER\n" + another.innerHTML + "\nlast\n" + another.textContent.trim() + "\nlastlast\n" + another.innerText);
                    console.log("[key] " + key);
                    if(!realmodelname.includes(key)){
                        x.style.display = "none";
                    }else{
                        x.style.display = "block";
                    }

                }
                catch (e) { //If element couldn´t be found
                    console.log("\t[ERROR] FindById(" + i + ")");
                }
            }


        }

</script> 

    <div class="container">
        <div class="row">
            <!--dejaremos 3 columnas a la derecha para aï¿½adir el total y nï¿½mero de clientes-->
            <div class="col-md-9" id="mainContainer">
                <div class="card" id="card">
                    <div class="card-header" id="card-header">
                        <h4> Listado de Gpus</h4>
                        <input type="text" onKeyup="findString()" id='searchbox'>
                    </div>
                    <div class="DivOfRegs">
                        <c:forEach var="gpu" items="${gpus}">
                            <div class="card text-white bg-dark mb-3" style="width: 18rem;" id="eachcard_${gpu.id}">
                                <img class="card-img-top" src="${gpu.imageurl}" alt="${gpu.imageurl}">
                                    <div class="card-body">
                                   
                                    <h5 class="card-title"  moai="${gpu.modelname}" id="cardtitle_${gpu.id}"  onclick='myfunction(this)' tag="${gpu.id}">${gpu.modelname}</h5>
                                    
                                    <!-- <p id="divIDNo${gpu.id}">EHHHH</p> -->

                                    <!-- HIDDEN/NOTHIDDEN -->
                                    <div id="hiddeableElement_${gpu.id}" class="eachTable">

                                        <table class="table table-condensed table-hover table-dark">
                                            <thead>
                                              <tr>
                                                <th scope="col">property</th>
                                                <th scope="col">value</th>
                                              </tr>
                                            </thead>
                                            <tbody>

                                              <tr>
                                                <td class="title">COMPANY</td>
                                                <td class="content">${gpu.company}</td>
                                              </tr>

                                              <tr>
                                                <td class="title">YEAR</td>
                                                <td class="content">${gpu.year}</td>
                                              </tr>

                                              <tr>
                                                <td class="title">MEMORY</td>
                                                <td class="content">${gpu.memory}</td>
                                              </tr>

                                              <tr>
                                                <td class="title">POWER</td>
                                                <td class="content">${gpu.power}</td>
                                              </tr>

                                              <tr>
                                                <td class="title">TYPE</td>
                                                <td class="content">${gpu.type}</td>
                                              </tr>

                                              <tr>
                                                <td class="title">SOCKET</td>
                                                <td class="content">${gpu.socket}</td>
                                              </tr>

                                            </tbody>
                                        </table>


                                        <!-- <p  class="card-text" id="company">company ${gpu.company}</p>
                                        <p  class="card-text" >year ${gpu.year}</p>
                                        <p  class="card-text" >memory<p> ${gpu.memory}</p></p>
                                        <p  class="card-text" >power ${gpu.power}</p>
                                        <p  class="card-text" >type ${gpu.type}</p>
                                        <p  class="card-text" >socket ${gpu.socket}</p> -->
                                    </div>

                                    <hr>
                                    <a  href="${pageContext.request.contextPath}/ServletController?accion=editGpu">Delete</a>
                                    <a  href="${pageContext.request.contextPath}/ServletController?accion=editGpu&id=${gpu.id}" class="btn btn-primary">${gpu.price} $</a>
                                </div>
                            </div>
                       </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</seccion>
<!-- 
    CREATE TABLE `mywebproject`.`gpus` ( `id` INT(11) NOT NULL AUTO_INCREMENT , `modelname` VARCHAR(50) NOT NULL , `company` INT(11) NOT NULL , `year` INT(11) NOT NULL , `memory` DOUBLE(11) NOT NULL , `power` DOUBLE(11) NOT NULL , `socket` VARCHAR(250) NOT NULL , `price` DOUBLE(11) NOT NULL , `type` ENUM('Low-Profile','Standard','Laptop') NOT NULL , `imageurl` VARCHAR(300) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;
 -->