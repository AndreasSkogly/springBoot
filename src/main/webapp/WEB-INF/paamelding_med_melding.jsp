<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="no">
<head>
<%--    <meta charset="UTF-8">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/simple.css">


    <script src="${pageContext.request.contextPath}/resources/js/simple.js" defer></script>


	<title>Påmelding</title>
</head>

<body>
<h2>Påmelding</h2>
<c:choose>
<c:when test="${not empty feilmelding}">
<p id="ugyldig_paamelding" style="color:red;">${feilmelding}</p>
</c:when>
    <c:when test="password !== password_rep">
        <p id="ugyldig_paamelding" style="color:red;">Passordet samsvarer ikke!</p>
    </c:when>
</c:choose>

<form id="paamelding_form"  action="/paameld" method="post">
    <p>Fornavn</p>
    <input type="text" id="fornavn" name ="fornavn" minlength="2" maxlength="20" required>

    <p>Etternavn</p>
    <input type="text" id ="etternavn" name ="etternavn" minlength="2" maxlength="20" required>

    <p>Mobil(8 siffer)</p>
    <input
            oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0,8);" <%--Denne linjen er hentet fra ChatGPT--%>
            type="tel" id="tlfNummer" name="mobil" maxlength="8"  minlength="8" required>

    <p>Passord</p>
    <input type="password" id="password" name="passord" minlength="8" maxlength="30" required>

    <p>Passord repetert</p>
    <input type="password" id="password_rep" name ="password_rep"  minlength="8" maxlength="30" required>

    <div class="kjonn_valg">
        <p>Kjønn</p>
        <span><input type="radio" id="mann" value="Mann" name="kjonn">Mann</span>
        <span><input type="radio" id="kvinne" value="Kvinne" name="kjonn">Kvinne</span>
    </div>

    <button type="submit" id="paameld">Meld meg på</button>

</form>

<!-- Jeg har fjernet alt som har med form og input å gjøre,
     siden dette er pensum. Her får dere sette opp skjemaet
     selv. Lykke til.
-->

</body>
</html>
