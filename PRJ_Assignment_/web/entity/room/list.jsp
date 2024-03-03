<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:38:49 AM
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
                <h2>Room List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Number</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeRoom}</p>
                    <c:forEach var="room" items="${rooms}">
                        <tr>
                            <td>${room.rid}</td>
                            <td>${room.rnumber}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
    </body>
</html>
