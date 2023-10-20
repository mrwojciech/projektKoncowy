<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trainer landing page</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/logout-link.jsp"/>
<p>Witaj</p>

<td>
    <sec:authorize access="hasRole('ADMIN')">
        <div>
            <a href='<c:url value="/view/trainer/add"/>'>Dodaj trenera</a>
        </div>
        <div>
            <a href='<c:url value="/view/trainee/add"/>'>Dodaj trainee</a>
        </div>

    </sec:authorize>
</td>
<div>
    <a href="/view/training/trainer/${trainings[0].trainer.id}">Moje treningi</a><br/>
</div>
<div>
    <h1>Trainings for Trainer</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Date and Time</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${trainings}" var="training">
            <tr>
                <td>${training.id}</td>
                <td>${training.user.username}</td>
                <td>${training.dateTime}</td>
                <td>${training.description}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
