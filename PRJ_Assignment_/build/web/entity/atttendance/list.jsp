<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:41:26 AM
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
        <div class="table">
                    <h2>Attendance List</h2>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Session ID</th>
                                <th>Student ID</th>
                                <th>Present</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                        <p> ${sizeattendance}</p>
                    <c:forEach var="attendance" items="${attendances}">
                        <tr>
                            <td>${attendance.aid}</td>
                            <td>${attendance.session.ssid}</td>
                            <td>${attendance.student.sid}</td>
                            <td>${attendance.isPresent}</td>
                            <td>${attendance.description}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
    </body>
</html>
