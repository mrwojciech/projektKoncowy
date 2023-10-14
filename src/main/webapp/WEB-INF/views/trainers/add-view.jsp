<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj Trenera</title>
    <link rel="stylesheet" type="text/css" href="/styles.css">
</head>
<body>
<form:form method="post" modelAttribute="trainer">
    First Name: <form:input path="firstName"/><form:errors path="firstName"/><br/>
    Last Name: <form:input path="lastName"/><form:errors path="lastName"/><br/>
    Password: <form:textarea path="password"/><form:errors path="password"/><br/>
    UserName <form:input path="username"/><form:errors path="username"/><br/>
    rating <form:input path="rating"/><form:errors path="rating"/><br/>
    <form:button>Dodaj</form:button>
</form:form>


</body>
</html>
