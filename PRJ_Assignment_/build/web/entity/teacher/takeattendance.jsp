<%-- 
    Document   : takeAttendance
    Created on : Feb 18, 2024, 6:54:09 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h2>Take Attendance</h2>
    <form action="attendance?tid=${tid}&ssid=${ssid}" method="post">
        <table border="1">
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Is Present</th>
            </tr>
            <c:forEach var="attendance" items="${requestScope.takeattendance}">
                <tr>
                    <td>${attendance.student.sid}</td>
                    <td>${attendance.student.sname}</td>
                    <td>
                        <input type="radio" name="isPresent_${attendance.aid}" value="T" ${attendance.isPresent eq 'T' ? 'checked' : ''}> Present
                        <input type="radio" name="isPresent_${attendance.aid}" value="F" ${attendance.isPresent eq 'F' ? 'checked' : ''}> Absent
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <input type="submit" value="Save Attendance">
        <!-- Include hidden fields for teacherId and sessionId -->
        <input type="hidden" name="tid" value="${tid}">
        <input type="hidden" name="ssid" value="${ssid}">
    </form>
    <button onclick="window.location.href='teacher?id=${tid}'">Back to teacher page</button>
</body>
</html>
