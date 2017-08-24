<%@ page import="java.util.ArrayList" %>
<%@ page import="model.JobOffer" %>
<%@ page import="model.Candidate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% ArrayList<JobOffer> jobOffers = (ArrayList<JobOffer>) request.getAttribute("jobOffers"); %>
<% Candidate candidate = (Candidate) request.getAttribute("candidate"); %>

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

<% if (candidate != null) { %>
<p>Pasujące oferty dla następującej osoby:</p>
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
        <td><%= candidate.getContactNumber() %>
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
<br>
<% } %>

<p>W wyniku Twojego zapytania otrzymano następujacy wynik:</p>

<% if (jobOffers.size() != 0) { %>
<% for (JobOffer jobOffer : jobOffers) { %>
<br><br>
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
    <tr>
        <td><b>Wynagrodzenie</b></td>
        <td><%= jobOffer.getMinSalary() %>  -  <%= jobOffer.getMaxSalary() %>
        </td>
    </tr>
</table>
<br><br>

<form action="CandidateServlet" method="post">
    <input type="hidden" name="jobOfferId" value="<%= jobOffer.getId() %>">
    <button type="submit" name="option" value="listMatching">Znajdz potencjalnych pracowników dla oferty</button>
</form>
<br><br>
<% }%>
<% } else { %>
<p><b>Nie znaleziono wyników dla ww. ogłoszenia</b></p>
<% } %>

<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>