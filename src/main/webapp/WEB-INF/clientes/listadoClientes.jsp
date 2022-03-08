<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<section id="clientes">
    <!--
    <h1>${usuarios.toString()}</h1>
    <h1>${usuarios.size()}</h1>
    <h1>${usuarios.get(0).toString()}</h1>
    -->
    <div class="container">
        <div class="row">
            <!--dejaremos 3 columnas a la derecha para aï¿½adir el total y nï¿½mero de clientes-->
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4> Listado de clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th> <!--Para el id -->
                                <th>Nombre</th> <!-- concatenaremos en esta columna nombre y apellidos -->
                                <th>Saldo</th>  
                                <th></th>   <!--dejamos una columna vacï¿½a -->
                            </tr>
                        </thead>
                        <tbody>
                            <!-- En el scope request podremos acceder a "clientes" para iterarlos -->
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr>
                                    <td>${usuario.id}</td>
                                    <td>${usuario.name} ${usuario.username}</td>
                                    <td>${usuario.password}</td>
                                    <td> 
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</seccion>