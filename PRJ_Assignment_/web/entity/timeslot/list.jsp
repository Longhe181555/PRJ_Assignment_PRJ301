<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:39:54 AM
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
                <h2>Time Slot List</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Start Hour</th>
                            <th>Start Minute</th>
                            <th>End Hour</th>
                            <th>End Minute</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizets}</p>
                    <c:forEach var="timeSlot" items="${timeslots}">
                        <tr>
                            <td>${timeSlot.tsid}</td>
                            <td>${timeSlot.startHour}</td>
                            <td>${timeSlot.startMinute}</td>
                            <td>${timeSlot.endHour}</td>
                            <td>${timeSlot.endMinute}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
    </body>
</html>
