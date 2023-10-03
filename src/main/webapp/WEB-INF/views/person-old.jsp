<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dodaj sobie osobę...</h1>
<form method="post">
    <div>
        <label for="login">Login:
            <input type="text" name="login" id="login"/>
        </label>
    </div>
    <div>
        <label for="email">Email:
            <input type="text" name="email" id="email"/>
        </label>
    </div>
    <div>
        <label for="password">Password:
            <input type="password" name="password" id="password"/>
        </label>
    </div>
    <div>
        <button type="submit">Dodaj</button>
    </div>
</form>
</body>
</html>
