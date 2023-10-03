<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuń wydawcę</title>
</head>
<body>
<h1>Usuń wydawcę</h1>
<p>Czy chcesz usunąć "${publisher.name}"?</p>
<form method="post">
    <input type="hidden" name="id" value="${publisher.id}"/>
    <button type="submit">Tak</button>
    | <a href="/view/publisher/list">Nie</a>
</form>
</body>
</html>
