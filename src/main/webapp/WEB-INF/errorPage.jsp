<%--
  Created by IntelliJ IDEA.
  User: D'Antuono
  Date: 05/07/2024
  Time: 22:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Errore</title>
    <script>
        function returnHome() {
            window.location.replace("index.jsp");
        }
    </script>
</head>
<body>
C'è stato un errore nella richiesta.<br>
Tra qualche secondo tornerai alla homepage...
<script>
    setTimeout(returnHome(), 100);
</script>
</body>
</html>
