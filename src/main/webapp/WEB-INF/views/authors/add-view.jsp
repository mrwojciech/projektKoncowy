<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj autora</title>
</head>
<body>
<form:form method="post" modelAttribute="author">
    Imię: <form:input path="firstName"/><br/>
    Nazwisko: <form:input path="lastName"/><br/>
    Pesel: <form:input path="pesel"/><br/>
    Email: <form:input path="email"/><br/>
    <form:errors path="*"/>
    <form:button>Dodaj</form:button>
</form:form>
</body>
</html>
