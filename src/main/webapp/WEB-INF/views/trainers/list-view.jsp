<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trainers</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/logout-link.jsp"/>
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
                <td>${trainer.user}</td>
                <td>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="/view/trainer/update?trainerId=${trainer.id}">Edytuj</a><br/>
                            <a href="/view/trainer/delete?id=${id}">Usuń</a><br/>
                        </sec:authorize>
<%--                        <input type="hidden" name="traineeId" value="${trainee.id}"/>--%>
<%--                        <a href="/view/training/add?trainerId=${trainer.id}&userId=${userId}">Zapisz sie na trening</a>--%>
                        <a href="/view/trainer/${trainer.id}/schedule">Pokaż dostępność trenera</a>
                        <a href="/view/trainer/addToFavorite?id=${id}">Obserwuj</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
