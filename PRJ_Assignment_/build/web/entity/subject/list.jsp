<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:37:59 AM
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
                <h2>Subject List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeSubject}</p>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.subid}</td>
                            <td>${subject.subname}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
    </body>
</html>
