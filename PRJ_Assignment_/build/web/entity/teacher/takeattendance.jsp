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
    <form action="takeattendance" method="post">
        <table border="1">
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Is Present</th>
                <th>Description</th> <!-- New column for the text box -->
            </tr>
            <c:forEach var="attendance" items="${takeattendance}">
                <tr>
                    <td>${attendance.student.sid}</td>
                    <td>${attendance.student.sname}</td>
                    <td>
                        <input type="radio" name="present_${attendance.student.sid}" value="T" ${attendance.isPresent eq 'T' ? 'checked' : ''}> Present
                        <input type="radio" name="present_${attendance.student.sid}" value="F" ${attendance.isPresent eq 'F' ? 'checked' : ''}> Absent
                    </td>
                    <td>
                        <!-- Add a description text box with a unique name -->
                        <input type="text" name="description_${attendance.student.sid}" value="${attendance.description}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <input type="submit" value="Save Attendance">
        <input type="hidden" name="ssid" value="${param.ssid}">
        <input type="hidden" name="tid" value="${param.tid}">
    </form>
    <button onclick="window.location.href='teacher/timetable?id=${param.tid}'">Back to timetable page</button>
</body>
</html>
