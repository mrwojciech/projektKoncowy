<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Błędy</title>
</head>
<body>
<div>
    Walidacja książki: ${book}
</div>
<div>
    <table>
        <tr>
            <th>Pole</th>
            <th>Niepoprawna wartość</th>
            <th>Komunikat</th>
        </tr>
        <c:forEach items="${violations}" var="violation">
            <tr>
                <td>${violation.propertyPath}</td>
                <td>${violation.invalidValue}</td>
                <td>${violation.message}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
