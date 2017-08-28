<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Brak wyników</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Nie udało się znaleźć wyniku o podanych kryteriach</h1>
<br><br>
<a href="javascript:history.back()"><b>Powrót do poprzedniej Strony</b></a>
<br><br>
<a href="index.jsp">Powrót do Strony Głównej</a><br><br>
<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>
