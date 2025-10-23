<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="no">
<head>
<%--    <meta charset="UTF-8">--%>
	<link href="../../resources/static/css/simple.css" rel="stylesheet" type="text/css" />

<script src="../../resources/static/js/simple.js" defer></script>

<!-- <script src="js/myscript.js" defer></script>  -->

	<title>Påmelding</title>
</head>

<body>
	<h2>Påmelding</h2>
	<p style="color:red;">Påmeldingsdetaljer er ugyldige</p>

    <form id="paamelding_form"  action="/paameld" method="post">
        <p>Fornavn</p>
        <input type="text" id="fornavn" name ="fornavn" minlength="2" maxlength="20" required>

        <p>Etternavn</p>
        <input type="text" id ="etternavn" name ="etternavn" minlength="2" maxlength="20" required>

        <p>Mobil(8 siffer)</p>
        <input type="tel" id="tlfNummer" name="mobil" maxlength="8"  minlength="8" required>

        <p>Passord</p>
        <input type="password" id="password" name="password" minlength="8" maxlength="30" required>

        <p>Passord repetert</p>
        <input type="password" id="password_rep" name ="password_rep"  minlength="8" maxlength="30" required>

    <div class="kjonn_valg">
        <p>Kjønn</p>
        <label><input type="radio" id="mann" name="kjonn">Mann</label>
        <label><input type="radio" id="kvinne" name="kjonn">Kvinne</label>
    </div>

        <button type="submit" id="paameld">Meld meg på</button>

    </form>

	<!-- Jeg har fjernet alt som har med form og input å gjøre,
		 siden dette er pensum. Her får dere sette opp skjemaet
		 selv. Lykke til.
	-->

</body>

</html>
