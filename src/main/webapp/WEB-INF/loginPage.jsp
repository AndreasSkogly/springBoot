<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/simple.css">
</head>
<body>


<h1>Logg Inn</h1>
<p style="color:red">${redirectMessage}</p>
<form action="${pageContext.request.contextPath}/loginPage/login" method="post">
    <fieldset>
        <label>Mobil:</label>
        <input type="text" name="mobil"/><br>
        <label>Passord:</label>
        <input type="password" name="passord"/> <br>
        <input type="submit" value="Logg inn"/><br>
    </fieldset>
</form>

</body>
</html>

