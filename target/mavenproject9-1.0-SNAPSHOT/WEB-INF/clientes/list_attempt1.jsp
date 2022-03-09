<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

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


        #searchbox {
            /*position: absolute;*/
            border: 1px solid black;
            background-color: #141e30;
            margin-left: 500px;
        }

	</style>

    <script type = "text/javascript">
        //CALL THIS ON WINDOW LOAD
        window.onload = function() {
            console.log("Function Called On Load")

            for (let i = 0; i < 200; i++) {
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

        //SHOW
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
            
            /*
            if(searchbox.value = "SEARCH"){
                searchbox.value = "";
            } */ 

            var key = searchbox.value;
       
            for (let i = 1; i < 200; i++) {
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

        //EXPORT DATA TO A MYSQL USEFUL IMPORT 
        //code from https://ourcodeworld.com/articles/read/189/how-to-create-a-file-and-generate-a-download-with-javascript-in-the-browser-without-a-server
        
        //LET ID FOR COMPANY TABLE
        var id = 0
        var imageurl = ""
        function letID(id, company, imageId){
            console.log('Let ID: ' + id)
            console.log('Let COMPANY: ' + company)
            var companymodelnameEl = document.getElementById('modalcompanyname');
            companymodelnameEl.innerText = company;

            var companyidEl = document.getElementById('modalcompanyid');
            companyidEl.innerText = 'Company ID ' + id;

            console.log('ImageId: ' + imageId);
            imageurl = '${companys.get(imageId)}';
            imageurl = imageurl.innerText;

            console.log('IMAGEURL: ' + imageurl);

        }
        
        
        /*function download(filename, text) {

            var filename = "download.txt";
            var text = "Code from https://ourcodeworld.com/articles/read/189/how-to-create-a-file-and-generate-a-download-with-javascript-in-the-browser-without-a-server";
            var other = ""
            //Read local data
            fetch("C:/Users/Jaime/Documents/NetBeansProjects/mavenproject9/salida.txt")
                .then(response => response.text())
                .then(text => other = text)
                


            var element = document.createElement('a');
            element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
            element.setAttribute('download', filename);

            element.style.display = 'none';
            document.body.appendChild(element);

            element.click();

            document.body.removeChild(element);



        }*/

</script> 
  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="modalcompanyname">NOT WORKING</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" id="modalcompanyid">
            
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

    <div class="container">
        <div class="row">
            <!--dejaremos 3 columnas a la derecha para aï¿½adir el total y nï¿½mero de clientes-->
            <div class="col-md-9" id="mainContainer">
                <div class="card" id="card">
                    <div class="card-header" id="card-header">
                        <h4> Listado de Gpus</h4>

                        <div id="subheader">
                            
                            <a  href="${pageContext.request.contextPath}/ServletController?accion=AddGpu">
                                <button type="button" class="btn btn-outline-warning">Add</button>
                            </a>

                            <a  href="https://github.com/">
                                <button type="button" class="btn btn-outline-warning">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-github" viewBox="0 0 16 16">
                                        <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"/>
                                    </svg>
                                </button>
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletController?accion=export">
                                <button type="button" class="btn btn-outline-warning" onclick="download()">
                                    <!-- <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-github" viewBox="0 0 16 16">
                                        <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"/>
                                    </svg> -->
                                EXPORT
                                </button>
                            </a>
                            <a  href="${pageContext.request.contextPath}/ServletController?accion=console">
                                <button type="button" class="btn btn-outline-warning">Console</button>
                            </a>
                            <input type="text" onKeyup="findString()" id='searchbox' value="">
                        </div>

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
                                                <td class="title" >COMPANY</td>
                                                <td class="content">
                                                    <!-- <a href="${pageContext.request.contextPath}/ServletController?accion=showCompany&id=${gpu.company}">${gpu.company}</a> -->
                                                    <button type="button" class="btn btn-outline-light" data-toggle="modal" data-target="#exampleModal" 
                                                        onclick="letID('${gpu.id}','${gpu.company}','${gpu.id}')">
                                                            ${gpu.company}
                                                    </button>
                                                </td>

                                                <!-- Button trigger modal -->

  
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
                                    <a  href="${pageContext.request.contextPath}/ServletController?accion=deleteGpu&id=${gpu.id}">Delete</a>
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