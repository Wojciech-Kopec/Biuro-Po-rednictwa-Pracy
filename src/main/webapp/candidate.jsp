<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Panel Kandytata(-ów)</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Panel Kandytata(-ów)</h1>
<form action="CandidateServlet" method="post">
    <label for="lastName">Nazwisko jest parametrem wyszukiwania</label><br>
    <input placeHolder="Nazwisko" type="text" name="lastName" id="lastName">
    <br>
    <input placeHolder="Imię" type="text" name="firstName">
    <br>
    <input placeHolder="PESEL" type="text" name="PESEL">
    <br>
    <select name="sex">
        <option value="" disabled selected>Płeć</option>
        <option value="M">Mężczyzna</option>
        <option value="K">Kobieta</option>
    </select>
    <br>
    <input placeHolder="Miasto" type="text" name="city">
    <br>
    <input placeHolder="Adres zamieszkania" type="text" name="address">
    <br>
    <input placeHolder="Numer kontaktowy" type="text" name="contactNumber">
    <br>
    <input placeHolder="Adres e-mail" type="text" name="emailAddress">
    <br>
    <label for="textarea">Umiejętności<br>(Wprowadzaj kolejne po znaku ENTER)</label><br>
    <textarea placeHolder="Doświadczenie" cols="20" rows="7" name="experiences" id="textarea"></textarea>
    <br><br>

    Szukaj: <input type="radio" name="option" value="search"><br>
    Dodaj: <input type="radio" name="option" value="add"><br>
    Modyfikuj: <input type="radio" name="option" value="update"><br>
    Usuń: <input type="radio" name="option" value="delete"><br><br>
    Pokaż wszystkie: <input type="radio" name="option" value="listAll">
    <br><br>
    <input type="submit" value="Wyślij">
</form>

<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>