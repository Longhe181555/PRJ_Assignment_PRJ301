<%-- 
    Document   : list
    Created on : Mar 15, 2024, 1:32:33 PM
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
            <h2></h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Published Date</th>
                        <th>Online</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="art" items="${artis}">
                    <tr>
                       <td>${art.aid}</td>
                       <td>${art.title}</td>
                       <td>${art.published_date}</td>
                       <td>${art.online}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
