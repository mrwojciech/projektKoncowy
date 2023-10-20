<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moje treningi</title>
</head>
<body>
<h1>Moje treniengi...</h1>
<div>
    <a href="/view/trainee/list">Nasi trainee</a><br/>
</div>


<jsp:include page="/WEB-INF/views/logout-link.jsp"/>

<table>
    <tr>
        <th>Id</th>
        <th> username</th>
        <th> firstName</th>
        <th> lastName</th>
        <th> active</th>
        <th>Date and Time</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${trainings}" var="training">
        <tr>
            <td>${training.id}</td>
            <td>${training.trainer.username}</td>
            <td>${training.trainer.firstName}</td>
            <td>${training.trainer.lastName}</td>
            <td>${training.trainer.active}</td>
            <td>${training.dateTime}</td>
            <td>${training.description}</td>
            <td>actions</td>
            <td>
                <sec:authorize access="isAuthenticated()">
                    <a href="/view/trainer/releaseSlot?trainingId=${training.id}">Usuń</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
