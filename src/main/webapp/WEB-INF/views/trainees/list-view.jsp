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
<jsp:include page="/WEB-INF/views/logout-link.jsp"/>
<div>
    <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>
</div>
<div>
    <a href='<c:url value="/view/trainee/add"/>'>Dodaj trainee</a>
</div>

<div>
    <table>
        <tr>
            <th>Treinee_id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th>Password</th>
                <th>Role</th>
                <th>UserName</th>
                <th>User_Id</th>
                <th>Is Trainer</th>
            </sec:authorize>
        </tr>
        <c:forEach items="${trainees}" var="trainee">
            <tr>
                <td>${trainee.id}</td>
                <td>${trainee.firstName}</td>
                <td>${trainee.lastName}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>${trainee.password}</td>
                    <td>${trainee.role}</td>
                    <td>${trainee.username}</td>
                    <td>${trainee.user.id}</td>
                    <td>${trainee.user.trainer}</td>
                </sec:authorize>

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
