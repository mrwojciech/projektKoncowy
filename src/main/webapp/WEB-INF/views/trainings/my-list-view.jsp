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
Witaj ${user.firstName} ${user.lastName}, poniżej znajduje się lista Twoich treningow
<jsp:include page="/WEB-INF/views/logout-link.jsp"/>
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
    <c:forEach items="${myTrainings}" var="training">
        <tr>
            <td>${training.id}</td>
            <td>${training.trainer}</td>
            <td>${training.dateTime}</td>
            <td>${training.description}</td>
            <td>
                <sec:authorize access="isAuthenticated()">
                    <a href="/view/training/update?id=${training.id}">Edytuj</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
