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
                Registrazione lettore
            </h3>
        </div>
        <form action="/registrazione" method="post">
            <div class="row mb-3 form-floating">
                <input name="email" type="text" class="form-control" id="validationMail" placeholder required>
                <label for="validationMail" class="form-label">e-mail</label>
            </div>
            <div class="row mb-3 form-floating">
                <input name="password" type="password" class="form-control" id="validationPassword" placeholder required>
                <label for="validationPassword" class="form-label">Password</label>
            </div>
            <div class="row mb-3 form-floating">
                <input name="nickname" type="text" class="form-control" id="validationNickname" placeholder required>
                <label for="validationNickname" class="form-label">Nickname</label>
            </div>
            <div class="row mb-3 form-floating">
                <input name="scuderia" type="text" class="form-control" id="scuderia" placeholder>
                <label for="scuderia" class="form-label">Scuderia Preferita</label>
            </div>
            <div class="row">
                <button class="btn btn-primary mb-5" type="submit" value="registrati">Registrati</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>