<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/simple.css">

    <title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table>
		<tr>
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
           <%-- <tr>
				<td align="center">&#9792;</td>
				<td>Anne Panne</td>
				<td>234 56 789</td>
			</tr>

            <tr>
				<td align="center">&#9794;</td>
				<td>Arne Arnesen</td>
				<td>901 23 456</td>
			</tr>

            <tr>
				<td align="center">&#9794;</td>
				<td>Lars-Petter Helland</td>
				<td>123 45 679</td>
			</tr>

            <tr>
				<td align="center">&#9794;</td>
				<td>Per Viskelær</td>
				<td>345 34 534</td>
			</tr>

            <tr>
				<td align="center">&#9792;</td>
				<td>Xx-x Xxx</td>
				<td>123 21 378</td>
			</tr>&ndash;%&gt;--%>


    <c:forEach var="deltaker" items="${deltagere}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${deltaker.kjonn == 'Mann'}">&#9794;</c:when>
                    <c:otherwise>&#9792;</c:otherwise>
                </c:choose>
            </td>
            <td> ${deltaker.fornavn} ${deltaker.etternavn} </td>


            <td> ${deltaker.mobil}</td>

        </tr>
    </c:forEach>


	</table>
</body>
</html>