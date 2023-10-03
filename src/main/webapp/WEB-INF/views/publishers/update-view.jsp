<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edytuj wydawcę</title>
</head>
<body>
<form:form method="post" modelAttribute="publisher">
    Imię: <form:input path="name"/><br/>
    NIP: <form:input path="nip"/><br/>
    REGON: <form:input path="regon"/><br/>
    <form:errors path="*"/>
    <form:hidden path="id"/>
    <form:button>Edytuj</form:button>
</form:form>
</body>
</html>
