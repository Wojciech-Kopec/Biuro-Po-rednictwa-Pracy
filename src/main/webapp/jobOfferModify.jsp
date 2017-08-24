<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Panel Ofert Pracy</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Panel Ofert Pracy</h1>
<form action="JobOfferServlet" method="post">
    <input placeHolder="Pozycja" type="text" name="position">
    <br>
    <label for="companyName">Nazwa firmy jest parametrem wyszukiwania</label><br>
    <input placeHolder="Nazwa firmy" type="text" name="companyName" id="companyName">
    <br>
    <textarea placeHolder="Opis oferty pracy" cols="20" rows="5" name="description"></textarea>
    <br>
    <input placeHolder="Wymiar pracy" type="text" name="workingHours">
    <br>
    <input placeHolder="Typ zatrudnienia" type="text" name="contractType">
    <br>
    <input placeHolder="Wymagany czas stażu pracy " type="text" name="requiredYearsOfExperience">
    <br>
    <label for="textarea">Wymagane Kwalifikacje<br>(Wprowadzaj kolejne po znaku ENTER)</label><br>
    <textarea placeHolder="Wymagane kwalifikacje" cols="20" rows="5" name="requiredQualifications" id="textarea"></textarea>
    <br>
    <input placeHolder="Minimalna pensja" type="text" name="minSalary">
    <br>
    <input placeHolder="Maksymalna pensja" type="text" name="maxSalary">
    <br>

    Wykonaj modyfikikacje: <input type="radio" name="option" value="update"><br>

    <br><br><br>
    <input type="submit" value="Wyślij">
</form>

<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>