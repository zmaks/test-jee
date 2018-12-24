<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Account</title>
</head>

<body>
    <c:if test="${cardInfo != null}">
        <p>Successfully login</p>
        Card number: ${cardInfo.getCardNumber()}
        <br>
        Amount: ${cardInfo.getAmount()}
        <br>
        User: ${cardInfo.getUserFirstName()} ${cardInfo.getUserLastName()}
    </c:if>
    <c:if test="${cardInfo == null}">
        <a href="/index.jsp">Please, enter pin!</a>x
    </c:if>
</body>
</html>