<%-- Created by IntelliJ IDEA. User: D'Antuono Date: 05/07/2024 Time: 22:18 --%>
<%--index.jsp: mostra una homepage con le discussioni e le categorie principali--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="unisa.it.formulaonline.model.entity.Categoria"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="resources/css/formulaonline.css">
    <title>Formula Online</title>
</head>

<body>
<%@include file="/WEB-INF/header.jsp"%>
<div class="container main h-100 pt-4 pb-4">
    <h2>Benvenuto su</h2>
    <h1>FormulaOnline</h1>
    <div class="row d-flex justify-content-center h-100">
        <div class="col-10">
            <div class="d-flex justify-content-between mb-4">
                <h3 class="text-black">Discussioni principali</h3>
            </div>
            <ol class="list-group">
                <c:forEach items="${requestScope.discussioniPrincipali}" var="dis">
                    <li class="list-group-item">
                        <div class="row row-cols-sm-2 row-cols-1 flex-wrap" id="${dis.idDiscussione}">
                                <div class="col col-md-8">
                                    <a class="stretched-link text-decoration-none"
                                        href="discussione?idDiscussione=${dis.idDiscussione}">
                                        ${dis.titolo}</a>
                                    <p class="small">
                                        in ${dis.categoria.nome}
                                    </p>
                                    </div>
                                <div class="col col-md-4">
                                    <small>creato da:</small>
                                        ${dis.lettore.nickname}
                                    <p class="small">
                                        # commenti: ${dis.numeroCommenti}
                                    </p>
                                </div>
                        </div>
                    </li>
                </c:forEach>
            </ol>
        </div>
        <div class="col-10">
            <div class="d-flex justify-content-between mb-4">
                <h3 class="text-black">Categorie principali</h3>
            </div>
            <ol class="list-group">
                <c:forEach items="${requestScope.categorie}" var="cat">
                    <li class="list-group-item">
                        <div class="" id="${cat.idCategoria}">
                            <a class="stretched-link text-decoration-none"
                                href="categoria?idCategoria=${cat.idCategoria}">
                                ${cat.nome}</a>
                            <div class>
                                ${cat.descrizione}
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>


</body>

</html>