<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>MyCloud</title>
    <link rel="stylesheet" href="../../resources/css/Autoriz_form.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
</head>
<body>
<div class="reg">
    <form method="POST" action="${contextPath}/login" id="slick-login">
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <label for="username">Login:</label><input type="text" name="username" placeholder="Your login">

            <label for="password">Password:</label><input type="password" name="password" placeholder="Your password">
            <span>${error}</span>
            <input type="hidden" name="userName" value="${pageContext.request.userPrincipal.name}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign in">
            <input type="submit" onClick="window.open('${contextPath}/registration')" value="Registration">
        </div>
    </form>
</div>
</body>
</html>