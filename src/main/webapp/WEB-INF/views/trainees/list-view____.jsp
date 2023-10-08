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
    <a href="/view/trainee/add">Dodaj użytkownika</a><br/>
</div>
<div>
    <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>
</div>
<div>
    <table>
        <tr>
            <th>Treinee_id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Password</th>
            <th>Role</th>
            <th>UserName</th>
            <th>Is Trainer</th>
            <th>Is Active</th>
            <th>User_Id</th>
        </tr>
        <c:forEach items="${allTraineesWithUserIds}" var="allTraineesWithUserId">
            <tr>
                <td>${allTraineesWithUserId[0].id}</td>
                <td>${allTraineesWithUserId[0].user.firstName}</td>
                <td>${allTraineesWithUserId[0].user.lastName}</td>
                <td>${allTraineesWithUserId[0].user.password}</td>
                <td>${allTraineesWithUserId[0].user.role}</td>
                <td>${allTraineesWithUserId[0].user.username}</td>
                <td>${allTraineesWithUserId[0].user.isTrainer}</td>
                <td>${allTraineesWithUserId[0].user.active}</td>
                <td>${allTraineesWithUserId[1].id}</td>
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
