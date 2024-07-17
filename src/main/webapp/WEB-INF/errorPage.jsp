<%--
  Created by IntelliJ IDEA.
  User: D'Antuono
  Date: 05/07/2024
  Time: 22:18
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Errore</title>
</head>
<body>
C'è stato un errore nella richiesta.<br>
Tra qualche secondo tornerai alla homepage...
<%
    response.sendRedirect("home");
%>
</body>
</html>
