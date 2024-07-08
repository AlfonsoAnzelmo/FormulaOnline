<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="resources/css/formulaonline.css">
    <title>Visualizza Segnalazioni</title>
    <script src="segnalazione.js"></script>
</head>

<body>
    <div class="container main h-100 pt-4 pb-4">
        <div class="row d-flex justify-content-center h-100">
            <div class="col-10">
                <div class="d-flex justify-content-between mb-4">
                    <h3 class="text-black">Segnalazioni</h3>
                </div>

                <ol class="list-group">
                    <c:forEach items="${requestScope.segnalazioni}" var="segn">
                        <li class="list-group-item">
                            <div class="container-fluid row-cols-auto" id="${segn.idSegnalazione}">
                                <div>
                                    Inviata da: ${segn.autore.nome} <br>
                                    Motivazione: ${segn.corpo}
                                </div>
                                <hr>
                                <div class="flex-wrap">
                                    <b>Autore:</b>
                                    ${segn.commento.autore}
                                    <br>
                                    <b>Commento:</b>
                                    ${segn.commento.corpo}
                                </div>
                                <div class="align-self-end">
                                    <form class="col" method="post" action="eliminaSegnalazione">
                                        <input type="hidden" name="codice" value="${segnalazione.idSegnalazione}">
                                        <button class="btn btn-primary" type="submit">
                                            Elimina
                                        </button>
                                    </form>

                                    <button type="button" class="btn btn-danger col" data-bs-toggle="modal"
                                        data-bs-target="#sospendiModal" onclick="impostaData()">
                                        Sospendi
                                    </button>

                                    <div class="modal fade" id="sospendiModal" tabindex="1"
                                        aria-labelledby="sospendiModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="sospendiModalLabel">Sospendi
                                                    </h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Annulla"></button>
                                                </div>
                                                <form action="sospendi" method="post">
                                                    <div class="modal-body">
                                                        <input id="dataFine" type="date" name="dataFineSospensione" />
                                                        <input type="hidden" name="codice"
                                                            value="${segnalazione.idSegnalazione}">
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">Annulla</button>
                                                        <button type="submit" class="btn btn-danger">
                                                            Sospendi</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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