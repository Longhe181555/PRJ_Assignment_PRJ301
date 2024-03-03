<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:43:48 AM
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
            <h2>Account List</h2>
            <table border="1">
                
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>ID</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.username}</td>
                        <td>${account.password}</td>
                        <td>${account.id}</td>
                        <td>${account.role}</td>
                    </tr>
                </c:forEach>
            </table>
          </div>
    </body>
</html>
