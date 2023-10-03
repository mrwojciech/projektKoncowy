<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moje książki</title>
</head>
<body>
Witaj ${user.firstName} ${user.lastName}, poniżej znajduje się lista Twoich książek

<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Rating</th>
        <th>Description</th>
        <th>Publisher</th>
        <th>Authors</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${myBooks}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.rating}</td>
            <td>${book.description}</td>
            <td>${book.publisher.name}</td>
            <td>
                <ul>
                    <c:forEach items="${book.authors}" var="author">
                        <li>${author.firstName} ${author.lastName}</li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <sec:authorize access="isAuthenticated()">
                    <a href="/view/book/removeFromFavorite?id=${book.id}">Nie lubię</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
