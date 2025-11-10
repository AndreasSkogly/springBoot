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

<p id="brukerInnlogget">
    Innlogget som: ${sessionScope.user_tlf} / ${sessionScope.user_navn}
</p>

	<h2>Deltagerliste</h2>
    <fieldset>
	<table>
		<tr>
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>

        <c:forEach var="deltaker" items="${deltagere}">
            <tr
                    <c:if test="${deltaker.mobil == sessionScope.user_tlf}">
                        style="background-color: lightgreen;"
                    </c:if>
            >
                <td align="center">
                    <c:choose>
                        <c:when test="${deltaker.kjonn == 'Mann'}">&#9794;</c:when>
                        <c:otherwise>&#9792;</c:otherwise>
                    </c:choose>
                </td>

 <td>
                        ${deltaker.fornavn} ${deltaker.etternavn}
                </td>

                <td>${deltaker.mobil}</td>
            </tr>
        </c:forEach>

	</table>

    <form action="${pageContext.request.contextPath}/logout" method="post">
            <p><input width="20%" type="submit" value="Logg ut" /></p>
        </fieldset>
    </form>
</body>
</html>