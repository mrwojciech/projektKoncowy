<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<form:form method="post" modelAttribute="user">
    <div>
        Nazwa: <form:input path="username"/><form:errors path="username"/>
    </div>
    <div>
        Hasło: <form:password path="password"/><form:errors path="password"/>
    </div>
    <div>
        <form:button>Zarejestruj</form:button>
    </div>
</form:form>
</body>
</html>
