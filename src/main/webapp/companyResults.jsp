<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Company" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<% ArrayList<Company> companies = (ArrayList<Company>) request.getAttribute("companies"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Wyniki akcji</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Wynik zapytania <%= request.getAttribute("option") %>
</h1>
<p>W wyniku Twojego zapytania otrzymano następujacy wynik:</p>
<% for (Company company : companies) { %>
<p>Nazwa: <%= company.getName() %><br>
    NIP: <%= company.getNIP() %><br>
    REGON: <%= company.getREGON() %><br>
    Miasto: <%= company.getCity() %><br>
    Adres: <%= company.getAddress() %><br>
    Numer kontaktowy: <%= company.getContactNumber() %><br>
    Adres e-mail: <%= company.getEmailAddress() %><br>
</p>
<br><br><br>
<form action="JobOfferServlet" method="post">
    <input type="hidden" name="companyName" value="<%= company.getName() %>">
    <button type="submit" name="option" value="listCompanyOffers">Znajdz oferty pracy dla firmy
        <%= company.getName() %>
    </button>
</form>
<% } %>


<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>