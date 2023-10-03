<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Książki</title>
</head>
<body>
<div>
    <a href='<c:url value="/view/book/add"/>'>Dodaj książkę</a>
</div>
<div>
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
        <c:forEach items="${books}" var="book">
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
                        <sec:authorize access="hasRole('ADMIN')">
                        <a href="/view/book/update?id=${book.id}">Edytuj</a><br/>
                        <a href="/view/book/delete?id=${book.id}">Usuń</a><br/>
                        </sec:authorize>
                        <a href="/view/book/addToFavorite?id=${book.id}">Ulubiona</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
