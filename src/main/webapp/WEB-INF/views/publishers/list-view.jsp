<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Wydawcy</title>
</head>
<body>
<div>
    <a href='<c:url value="/view/publisher/add"/>'>Dodaj wydawcę</a>
</div>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${publishers}" var="publisher">
            <tr>
                <td>${publisher.id}</td>
                <td>${publisher.name}</td>
                <td><a href="/view/publisher/update?id=${publisher.id}">Edytuj</a><br/>
                    <a href="/view/publisher/delete?id=${publisher.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
