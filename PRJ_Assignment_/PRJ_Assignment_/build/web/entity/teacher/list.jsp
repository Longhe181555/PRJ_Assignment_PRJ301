<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:34:02 AM
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
                <h2>Teacher List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeteacher}</p>
                    <c:forEach var="teacher" items="${teachers}">
                        <tr>
                            <td>${teacher.tid}</td>
                            <td>${teacher.tname}</td>
                            <td>${teacher.tgmail}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
                    
    </body>
</html>
