<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="unisa.it.formulaonline.model.entity.Categoria" %>
<%@ page import="unisa.it.formulaonline.model.entity.Discussione" %>
<%@ page import="unisa.it.formulaonline.model.entity.Lettore" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
<%@include file="/WEB-INF/header.jsp" %>
<div class="container main h-100 pt-4 pb-4">
    <div class="row d-flex justify-content-center h-100">
        <div class="col-10">
            <div class="d-flex justify-content-between mb-4">
                <h3 class="text-black">${requestScope.categoria.nome}</h3>
                <c:choose>
                    <c:when test="${sessionScope.lettore.getModeratore()}">
                        <form method="post" action="modificaCategoria">
                            <input type="hidden" name="idCategoria" value="${requestScope.categoria.idCategoria}">
                            <button class="btn btn-secondary" type="submit">
                                Modifica categoria
                            </button>
                        </form>
                    </c:when>
                </c:choose>
            </div>
            <div class=" mb-2">
                <c:choose>
                    <c:when test="${sessionScope.lettore.getModeratore()}">
                        <form method="post" action="nuovaCategoria">
                            <input type="hidden" name="idCategoria" value="${requestScope.categoria.idCategoria}">
                            <button class="btn btn-outline-secondary" type="submit">
                                + Nuova Categoria
                            </button>
                        </form>
                    </c:when>
                </c:choose>
                <form method="post" action="creaDiscussione">
                    <input type="hidden" name="idCategoria" value="${requestScope.categoria.idCategoria}">
                    <button class="btn btn-outline-secondary float-end">
                        + Nuova Discussione
                    </button>
                </form>
            </div>
            <ol class="list-group w-100">
                <c:forEach items="${requestScope.sottocategorie}" var="cat">
                    <li class="list-group-item">
                        <div class="" id="${cat.idCategoria}">
                            <a class="stretched-link" href="categoria?idCategoria=${cat.idCategoria}">
                                    ${cat.nome}</a>
                            <div class="m-2">
                                    ${cat.descrizione}
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ol>
            <hr>
            <ol class="list-group">
                <c:forEach items="${requestScope.discussioni}" var="dis">
                    <li class="list-group-item p-3">
                        <div class="" id="${dis.idDiscussione}">
                            <a class="stretched-link" href="discussione?idDiscussione=${dis.idDiscussione}">
                                    ${dis.titolo}</a>
                            <div class="">
                                    ${dis.autore}
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