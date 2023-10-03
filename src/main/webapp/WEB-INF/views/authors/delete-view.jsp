<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuń autora</title>
</head>
<body>
<h1>Usuń autora</h1>
<p>Czy chcesz usunąć "${author.firstName} ${author.lastName}"?</p>
<form method="post">
    <input type="hidden" name="id" value="${author.id}"/>
    <button type="submit">Tak</button> | <a href="/view/author/list">Nie</a>
</form>
</body>
</html>
