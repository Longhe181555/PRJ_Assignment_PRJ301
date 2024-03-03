<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:43:25 AM
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
                <h2>Grade List</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Exam Name</th>
                            <th>Subject Name</th>
                            <th>Student Name</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeGrade}</p>
                    <c:forEach var="grade" items="${grades}">
                        <tr>
                            <td>${grade.grid}</td>
                            <td>${grade.exam.assessment.aname}</td>
                            <td>${grade.exam.assessment.subject.subname}</td>
                            <td>${grade.student.sname}</td>
                            <td>${grade.score}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
    </body>
</html>
