<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuń książkę</title>
</head>
<body>
<h1>Usuń książkę</h1>
<p>Czy chcesz usunąć "${book.title}?</p>
<form method="post">
    <input type="hidden" name="id" value="${book.id}"/>
    <button type="submit">Tak</button> | <a href="/view/book/list">Nie</a>
</form>
</body>
</html>
