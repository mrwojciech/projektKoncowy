<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
</head>
<body>

    <jsp:include page="/WEB-INF/views/logout-link.jsp"/>

<%--<div>--%>
<%--    <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>--%>
<%--</div>--%>

<p>Strona domowa admina</p>

<td>
    <sec:authorize access="hasRole('ADMIN')">
        <div>
            <a href="/view/trainee/add">Dodaj użytkownika</a><br/>
        </div>
        <div>
            <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>
        </div>
        <div>
            <a href='<c:url value="/view/trainee/add"/>'>Dodaj trainee</a>
        </div>
    </sec:authorize>
</td>

<div>
    <table>
        <tr>
            <th>User Id</th>
            <th>Status (active?)</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.active}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="/view/user/update?userId=${user.id}">Edytuj</a><br/>
                            <a href="/view/user/delete?id=${user.id}">Usuń</a><br/>
                            <a href="/view/user/unactivate?id=${user.id}">UnActivate</a><br/>
                            <a href="/view/user/activate?id=${user.id}">Activate</a><br/>
                        </sec:authorize>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
