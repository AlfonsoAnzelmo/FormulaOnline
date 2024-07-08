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
        <div class="row d-flex justify-content-center h-100">
            <div class="col-10">
                <div class="d-flex justify-content-between mb-4">
                    <h3 class="text-black">Risultati per:${q}</h3>
                </div>
                <ul class="list-group">
                    <c:forEach items="${requestScope.risultati}" var="dis">
                        <li class="list-group-item">
                            <div class="" id="${dis.idDiscussione}">
                                <a class="stretched-link" href="discussione?idDiscussione=${dis.idDiscussione}">
                                    ${dis.titolo}</a>
                                <div class="">
                                    ${dis.autore}
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>

</html>