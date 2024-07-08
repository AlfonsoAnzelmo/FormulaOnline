<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<div class="container main h-100 pt-4 pb-4 justify-content-center">
    <div class="w-100 d-grid justify-content-center">
        <div class="row text-center p-3">
            <h3>
                Login lettore
            </h3>
        </div>

        <form action="login" method="post">
            <div class="row mb-3 form-floating">
                <input name="email" type="text" class="form-control" id="validationMail" placeholder required>
                <label for="validationMail" class="form-label">e-mail</label>
            </div>
            <div class="row mb-5 form-floating">
                <input name="password" type="password" class="form-control" id="validationPassword" placeholder required>
                <label for="validationPassword" class="form-label">Password</label>
            </div>
            <div class="row">
                <button class="btn btn-primary mb-5" type="submit" value="login">Accedi</button>
            </div>
        </form>
        <small>
        Non sei ancora iscritto? <a href="registrazione.jsp" class="text-decoration-none">Iscriviti ora!</a>
        </small>
    </div>
</div>
</body>
</html>