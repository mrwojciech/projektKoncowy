<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj książkę</title>
</head>
<body>
<form:form method="post" modelAttribute="book">
    Title: <form:input path="title"/><form:errors path="title"/><br/>
    Rating: <form:input path="rating"/><form:errors path="rating"/><br/>
    Description: <form:textarea path="description"/><form:errors path="description"/><br/>
    Pages <form:input path="pages"/><form:errors path="pages"/><br/>
    Publisher: <form:select path="publisher.id" items="${publisherList}" itemLabel="name" itemValue="id"/><form:errors path="publisher"/><br/>
    Author: <form:select path="authors" itemValue="id" itemLabel="lastName" items="${authorList}" multiple="true"/><form:errors path="authors"/><br/>
    <form:button>Dodaj</form:button>
</form:form>
</body>
</html>
