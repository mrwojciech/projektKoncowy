<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Autorzy</title>
    <link rel="stylesheet" href="/main.css" type="text/css">
</head>
<body>
<div>
    <a href='<c:url value="/view/author/add"/>'>Dodaj autora</a>
</div>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${authors}" var="author">
            <tr>
                <td>${author.id}</td>
                <td>${author.firstName}</td>
                <td>${author.lastName}</td>
                <td><a href="/view/author/update?id=${author.id}">Edytuj</a><br/>
                    <a href="/view/author/delete?id=${author.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
