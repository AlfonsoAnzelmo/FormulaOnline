<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="unisa.it.formulaonline.model.entity.Categoria" %>
<%@ page import="unisa.it.formulaonline.model.entity.Lettore" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.css">
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="resources/css/formulaonline.css">
    <title>Formula Online</title>
</head>

<body>
<%@include file="/WEB-INF/header.jsp" %>
<%
    Lettore l = (Lettore) session.getAttribute("utente");
    if(l==null || !l.getModeratore()){
        String redirectURL = ".";
        response.sendRedirect(redirectURL);
    }
%>
<div class="container main h-100 pt-4 pb-4">
    <div class="row d-flex justify-content-center h-100 px-5">
        <div class="container bg-white py-4">
            <p class="h3 text-center">
                Modifica Categoria
            </p>
            <form action="creacategoria" method="post" class="container w-100 m-2">
                <input class="form-control w-50 mb-3" maxlength="50"
                    placeholder="${categoria.nome}" id="nomecategoria" name="nomecategoria"
                    required>
                <textarea class="form-control mb-3" placeholder="descrizione"
                    maxlength="300">${categoria.descrizione}</textarea>
                    <label class="ms-3">Categoria</label>
                <select class="form-select mb-3" id="categoriaInput" name="nuovaCategoria">
                    <option selected>Nessuna</option>
                    <c:forEach items="${categorie}" var="cat">
                        <option name="${cat.idCategoria}">${cat.nome}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-primary">Crea</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>