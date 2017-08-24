<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Panel Firm</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Panel Firm</h1>
<form action="CompanyServlet" method="post">
    <label for="name">Nazwa firmy jest parametrem wyszukiwania</label><br>
    <input placeHolder="Nazwa" type="text" name="name" id="name">
    <br>
    <input placeHolder="NIP" type="text" name="NIP">
    <br>
    <input placeHolder="REGON" type="text" name="REGON">
    <br>
    <input placeHolder="Miasto" type="text" name="city">
    <br>
    <input placeHolder="Adres zamieszkania" type="text" name="address">
    <br>
    <input placeHolder="Numer kontaktowy" type="text" name="contactNumber">
    <br>
    <input placeHolder="Adres e-mail" type="text" name="emailAddress">
    <br><br><br>

    Szukaj: <input type="radio" name="option" value="search"><br>
    Dodaj: <input type="radio" name="option" value="add"><br>
    Modyfikuj: <input type="radio" name="option" value="update"><br>
    Usuń: <input type="radio" name="option" value="delete"><br><br>
    Pokaż wszystkie: <input type="radio" name="option" value="listAll">
    <br><br><br>
    <input type="submit" value="Wyślij">
</form>

<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>