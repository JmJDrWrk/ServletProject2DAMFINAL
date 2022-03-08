<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                
                <a href="#" class="btn btn-primary btn-block" data-bs-toggle="modal"
                   data-bs-target="#agregarClienteModal" href="${pageContext.request.contextPath}/ServletController?accion=AddGpu">
                    Add GPU
                </a>
            </div>
        </div>
    </div>
</section>