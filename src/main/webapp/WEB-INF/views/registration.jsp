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
    <form:form method="POST" modelAttribute="userForm" id="slick-login">


        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="username">Login:</label><form:input type="text" path="username"
                                                                placeholder="Create login"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>


        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="password">Password:</label><form:input type="password" path="password"
                                                                   placeholder="Create password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="password">Password:</label><form:input type="password" path="confirmPassword"
                                                                   placeholder="Confirm password"></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>
        <input type="submit" value="Create account">


    </form:form>
</div>
</body>
</html>