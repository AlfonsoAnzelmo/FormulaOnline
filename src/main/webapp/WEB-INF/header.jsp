<%-- Created by IntelliJ IDEA. User: D'Antuono Date: 05/07/2024 Time: 22:16 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="home">FormulaOnline</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarHeader">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse m-auto" id="navbarHeader">
                <form class="input-group w-auto m-auto" role="search" action="ricerca" method="get">
                    <input class="form-control" type="search" placeholder="Ricerca..." name="q">
                    <button class="btn btn-outline-secondary" type="submit">Ricerca</button>
                </form>
        <c:choose>
            <c:when test="${lettore!=null && lettore.moderatore}">
                <div class="nav-item px-3 dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       data-bs-target="#utenteDd" aria-expanded="false">
                            ${sessionScope.lettore.nickname}
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" >
                        <li><a class="dropdown-item" href="listaUtenti">Lista utenti</a></li>
                        <li><a class="dropdown-item" href="segnalazioni">Segnalazioni</a></li>
                        <li><a class="dropdown-item" href="aggiornaLettore.jsp">Area Utente</a></li>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </div>
            </c:when>
            <c:when test="${sessionScope.lettore!=null && !lettore.moderatore}">
                <div class="nav-item px-3 dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           data-bs-target="#utenteDd" aria-expanded="false">
                                ${sessionScope.lettore.nickname}
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" id="utenteDd">
                        <li><a class="dropdown-item" href="aggiornaLettore.jsp">Area Utente</a></li>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>

                    </ul>
                </div>
            </c:when>

            <c:otherwise>
                <a class="btn btn-primary" href="login.jsp">Login</a>
            </c:otherwise>
        </c:choose>
<!--
            <div class="row-cols-lg-1">
                <a type="button" class="btn btn-primary" href="areaUtente.jsp">Area Utente</a>
            </div>
-->
            </div>
        </div>
    </nav>
</body>

</html>