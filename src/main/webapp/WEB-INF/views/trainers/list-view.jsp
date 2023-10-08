<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trainees</title>
</head>
<body>
<%--<div>--%>
<%--    <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>--%>
<%--</div>--%>

<p>Nasi Trenerzy</p>

<td>
    <sec:authorize access="hasRole('ADMIN')">
        <div>
            <a href="/view/trainee/add">Dodaj użytkownika</a><br/>
        </div>
        <div>
            <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>
        </div>
    </sec:authorize>
</td>

<div>
    <table>
        <tr>
            <th>Trainer Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Rating</th>
            <th>UserName</th>
            <th>Is Trainer</th>
            <th> User id</th>
            <th>Links</th>
        </tr>
        <c:forEach items="${trainers}" var="trainer">
            <tr>
                <td>${trainer.id}</td>
                <td>${trainer.firstName}</td>
                <td>${trainer.lastName}</td>

                <td>${trainer.rating}</td>
                <td>${trainer.username}</td>
<%--                <td>${trainer.isTrainer}</td>--%>
                <td>${trainer.id}</td>
                <td>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="/view/trainee/update?id=${id}">Edytuj</a><br/>
                            <a href="/view/trainee/delete?id=${id}">Usuń</a><br/>
                        </sec:authorize>
                        <a href="/view/trainee/addToFavorite?id=${id}">Dodaj do ulubionych</a>
                        <a href="/view/trainee/addToFavorite?id=${id}">Obserwuj</a>
                        <a href="/view/trainee/addToFavorite?id=${id}">Umów się na trening</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
