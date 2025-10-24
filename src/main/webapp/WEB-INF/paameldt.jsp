<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<%--<meta charset="UTF-8">--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/simple.css">
<title>Påmeldingsbekreftelse</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/resources/js/simple.js" defer></script>
	<h2>Påmeldingsbekreftelse</h2>
	<p>Påmeldingen er mottatt for</p>
            <span>Fornavn: ${deltager.fornavn}</span><br>
            <span>Etternavn: ${deltager.etternavn}</span><br>
            <span>Mobil: ${deltager.mobil}</span><br>
            <span>Kjønn: ${deltager.kjonn}</span><br>



<%--    <span id="paameldt_fornavn">${fornavn}</span> <br>--%>
<%--    <span id="paameldt_etternavn">${etternavn}</span> <br>--%>
<%--    <span id="paameldt_kjonn">${kjonn}</span> <br>--%>
<%--    <span id="paameldt_nr">${mobil}</span> <br>--%>


<%--
 pageContext.request.contextPath gjør at den finner pathen til deltagerliste uansett hvor den kjører
--%>
    <a href="${pageContext.request.contextPath}/deltagerliste" > Gå til deltagerlisten </a>
</body>
</html>