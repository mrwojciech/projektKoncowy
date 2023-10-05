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
<div>
    <a href='<c:url value="/view/trainee/add"/>'>Dodaj trainee</a>
</div>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Password</th>
            <th>Role</th>
            <th>UserName</th>
            <th>Is Trainer</th>
            <th>Treinee_id</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.username}</td>
                <td>${user.isTrainer}</td>
                <td>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ADMIN')">
                        <a href="/view/trainee/update?id=${id}">Edytuj</a><br/>
                        <a href="/view/trainee/delete?id=${id}">Usuń</a><br/>
                        </sec:authorize>
                        <a href="/view/trainee/addToFavorite?id=${id}">Ulubiona</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
