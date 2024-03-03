<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:42:02 AM
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
                <h2>Assessment List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Subject ID</th>
                            <th>Name</th>
                            <th>Weight(%)</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p> ${sizeassessment}</p>
                    <c:forEach var="assessment" items="${assessments}">
                        <tr>
                            <td>${assessment.aid}</td>
                            <td>${assessment.subject.subid}</td>
                            <td>${assessment.aname}</td>
                            <td>${assessment.weight}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>   
    </body>
</html>
