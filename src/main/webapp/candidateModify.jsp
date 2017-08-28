<%@ page import="com.wojciech.kopec.model.Candidate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Panel Modyfikacji Kandytata</title>
</head>
<body>
<% Candidate candidate = (Candidate) request.getAttribute("candidate"); %>
<jsp:include page="/WEB-INF/header.jspf"/>
<h1>Panel Kandytata(-ów)</h1>
<form action="CandidateServlet" method="post">
    <label for="lastName">Nazwisko jest parametrem wyszukiwania</label><br>
    <input value="<%=candidate.getLastName()%>" type="text" name="lastName" id="lastName" disabled>
    <!-- input DISABLED are not submitted by default, input HIDDEN is used here as workaround -->
    <input value="<%=candidate.getLastName()%>" type="hidden" name="lastName">
    <br>
    <input value="<%= candidate.getFirstName() %>" type="text" name="firstName" disabled>
    <input value="<%= candidate.getFirstName() %>" type="hidden" name="firstName">
    <br>
    <input value="<%= candidate.getPESEL() %>" type="text" name="PESEL">
    <br>
    <input value="<%= candidate.getSex() %>" type="text" name="sex" disabled>
    <input value="<%= candidate.getSex() %>" type="hidden" name="sex">
    <br>
    <input value="<%= candidate.getCity() %>" type="text" name="city">
    <br>
    <input value="<%= candidate.getAddress() %>" type="text" name="address">
    <br>
    <input value="<%= candidate.getContactNumber() %>" type="text" name="contactNumber">
    <br>
    <input value="<%= candidate.getEmailAddress() %>" type="text" name="emailAddress">
    <br>
    <label for="textarea">Umiejętności<br>(Wprowadzaj kolejne po znaku ENTER)</label><br>
    <textarea cols="20" rows="7" name="experiences" id="textarea"><%=candidate.getExperiences().toString()
            .replaceAll("[\\[\\]]","").replaceAll(", ","\n")%></textarea>
    <br><br>

    <input type="hidden" name="option" value="modify">
    <br><br>
    <input type="submit" value="Wykonaj modyfikacje">
</form>

<jsp:include page="/WEB-INF/footer.jspf"/>
</body>
</html>