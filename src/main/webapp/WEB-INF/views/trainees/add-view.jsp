<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj Trainee</title>
</head>
<body>
<form:form method="post" modelAttribute="user">
    First Name: <form:input path="firstName"/><form:errors path="firstName"/><br/>
    Last Name: <form:input path="lastName"/><form:errors path="lastName"/><br/>
    Password: <form:textarea path="password"/><form:errors path="password"/><br/>
    Role <form:input path="role"/><form:errors path="role"/><br/>
    UserName <form:input path="username"/><form:errors path="username"/><br/>
    Is Trainer <form:input path="isTrainer"/><form:errors path="isTrainer"/><br/>
    <form:button>Dodaj</form:button>
</form:form>
</body>
</html>
