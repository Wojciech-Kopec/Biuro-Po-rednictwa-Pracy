<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wojciech.kopec.model.Candidate" %>
<%@ page import="com.wojciech.kopec.model.JobOffer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates"); %>
<% JobOffer jobOffer = (JobOffer) request.getAttribute("jobOffer"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Wynik akcji</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Wynik akcji <%= request.getAttribute("option") %>
</h1>

<% if (jobOffer != null) { %>
<p>Dopasowani kandydaci dla następującej oferty pracy:</p>
<table>
    <tr>
        <td><b>Data umieszczenie oferty</b></td>
        <td><%= jobOffer.getCreationDate() %>
        </td>
    </tr>
    <tr>
        <td><b>Stanowisko</b></td>
        <td><%= jobOffer.getPosition() %>
        </td>
    </tr>
    <tr>
        <td><b>Pracodawca</b></td>
        <td><%= jobOffer.getCompanyName() %>
        </td>
    </tr>
    <tr>
        <td><b>Opis stanowiska</b></td>
        <td><%= jobOffer.getDescription() %>
        </td>
    </tr>
    <tr>
        <td><b>Wymiar pracy</b></td>
        <td><%= jobOffer.getWorkingHours() %> Etat(u)
        </td>
    </tr>
    <tr>
        <td><b>Typ zatrudnienia</b></td>
        <td><%= jobOffer.getContractType() %>
        </td>
    </tr>
    <tr>
        <td><b>Wymagany okres zatrudnienia na podobnym stanowisku</b></td>
        <td><%= jobOffer.getRequiredYearsOfExperience() %> Lat(a)
        </td>
    </tr>
    <tr>
        <td><b>Wymagane kwalifikacje</b></td>
        <td><%= jobOffer.getRequiredQualifications() %>
        </td>
    </tr>
</table>
<br>
<% } %>

<p>W wyniku Twojego zapytania otrzymano następujacy wynik:</p>

<% if (candidates.size() != 0) { %>
<% for (Candidate candidate : candidates) { %>
<br><br>
<table>
    <tr>
        <td><b>Imię</b></td>
        <td><%= candidate.getFirstName() %>
        </td>
    </tr>
    <tr>
        <td><b>Nazwisko</b></td>
        <td><%= candidate.getLastName() %>
        </td>
    </tr>
    <tr>
        <td><b>PESEL</b></td>
        <td><%= candidate.getPESEL() %>
        </td>
    </tr>
    <tr>
        <td><b>Płeć</b></td>
        <td><%= candidate.getSex() %>
        </td>
    </tr>
    <tr>
        <td><b>Miasto</b></td>
        <td><%= candidate.getCity() %>
        </td>
    </tr>
    <tr>
        <td><b>Adres</b></td>
        <td><%= candidate.getAddress() %>
        </td>
    </tr>
    <tr>
        <td><b>Numer kontaktowy</b></td>
        <td><%=candidate.getContactNumber() %>
        </td>
    </tr>
    <tr>
        <td><b>Adres e-mail</b></td>
        <td><%= candidate.getEmailAddress() %>
        </td>
    </tr>
    <tr>
        <td><b>Umiejętności</b></td>
        <td><%= candidate.getExperiences() %>
        </td>
    </tr>
</table>
<br><br>

<form action="JobOfferServlet" method="post">
    <% Integer id = candidate.getId(); %>
    <input type="hidden" name="candidateId" value="<%= id %>">
    <button type="submit" name="option" value="listMatching">Znajdz oferty pracy dla
        <%= candidate.getFirstName()%> <%= candidate.getLastName()%>
    </button>
</form>
<br><br>
<% }%>
<% } else { %>
<p><b>Nie znaleziono wyników dla ww. kandydata</b></p>
<% } %>

<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>