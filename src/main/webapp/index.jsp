<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Login Page</title>
</head>

<body>
    <div style="color: red">
        <c:if test="${message != null}">${message}</c:if>
    </div>
    <form method="post" action="/cardLogin">

        Card number:
        <input type="text" name="cardNumber"/><br>

        PIN:
        <input type="password" name="pin"/>

        <input type="submit" value="submit">

    </form>
</body>
</html>