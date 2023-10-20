<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trainee landing page</title>
</head>
<body>
<div>
    <a href="/view/training/${trainee.id}">Moje treningi</a><br/>
</div>
<div>
    <a href="/view/trainer/list">Nasi trenerzy</a><br/>
</div>
<jsp:include page="/WEB-INF/views/logout-link.jsp"/>
<p>Witaj...</p>

<div>
    <table>
        <tr>
            <th>Trainee_id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role</th>
            <th>UserName</th>
            <th>Training Activity</th>
            <th>User_Id</th>
        </tr>
        <tr>
            <td>${trainee.id}</td>
            <td>${trainee.firstName}</td>
            <td>${trainee.lastName}</td>
            <td>${trainee.role}</td>
            <td>${trainee.username}</td>
            <td>${trainee.trainingActivity}</td>
            <td>${trainee.id}</td>
            <td>
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ADMIN')">
                        <a href="/view/trainee/update?id=${trainee.id}">Edytuj</a><br/>
                        <a href="/view/trainee/delete?id=${trainee.id}">Usuń</a><br/>
                    </sec:authorize>
                    <a href="/view/trainee/addToFavorite?id=${trainee.id}">Ulubiona</a>
                </sec:authorize>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
