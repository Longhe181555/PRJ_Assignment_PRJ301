<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:40:35 AM
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
                <h2>Session List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Group ID</th>
                            <th>Teacher ID</th>
                            <th>Room ID</th>
                            <th>TimeSlot ID</th>
                            <th>Date</th>
                            <th>Is Taken</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeSession}</p>
                    <c:forEach var="session" items="${sessions}">
                        <tr>
                            <td>${session.ssid}</td>
                            <td>${session.group.gid}</td>
                            <td>${session.teacher.tid}</td>
                            <td>${session.room.rid}</td>
                            <td>${session.timeslot.tsid}</td>
                            <td style="white-space: nowrap;">${session.date}</td>
                            <td>${session.isTaken}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div> 
    </body>
</html>
