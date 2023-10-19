<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Available Slots per Trainer</title>
</head>
<body>
<div>
    <a href="/view/trainer/list">Nasi trenerzy</a><br/>
</div>
<jsp:include page="/WEB-INF/views/logout-link.jsp"/>
<h1>Available Slots</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Slot ID</th>
        <th>Date</th>
<%--        <th>Time</th>--%>
        <th>Book this time</th>
    </tr>
    <c:forEach items="${schedule.availableSlots}" var="slot" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${slot.id}</td>
            <td>${slot.availableSlot.toLocalDate()}</td>
            <td>
                <a href="/view/trainer/bookSlot/${schedule.id}/${trainerId}/${slot.id}">${slot.availableSlot.toLocalTime()}</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
