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
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nickname</th>
        <th scope="col">email</th>
        <th scope="col">moderatore</th>
        <th scope="col"> scelta </th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${lettoriNonModeratori}" var="lettore" varStatus="loop">
        <tr>
            <th scope="row">${loop.index}</th>
            <td>${lettore.nickname}</td>
            <td>${lettore.email}</td>
            <td>${lettore.moderatore}</td>
            <td> <a href="nominaModeratore?idLettore=${lettore.idLettore}">rendi moderatore</a> </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

</body>

</html>