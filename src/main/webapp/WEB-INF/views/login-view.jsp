<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Zaloguj</title>
</head>
<body>
<form method="post" action="/login">
    Nazwa: <input type="text" name="username"/> Hasło: <input type="password" name="password"/>
    <button type="submit">Zaloguj</button>
</form>
</body>
</html>
