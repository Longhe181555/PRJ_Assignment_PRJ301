<%-- 
    Document   : search
    Created on : Feb 28, 2024, 12:06:46 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Student</title>
    </head>
    <body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Student</title>
    </head>
    <body>
        <form action="display" method="post">
            <button type="submit">Back to Display</button>
        </form>
        <h2>Search Students</h2>
        <!-- Insert button -->
        <form action="sinsert" method="GET">
            <input type="submit" value="Insert">
        </form>
        <form action="ssearch" method="post">
            <label for="sid">Student ID:</label>
            <input type="text" id="sid" name="sid">
            <br>

            <label for="sname">Student Name:</label>
            <input type="text" id="sname" name="sname">
            <br>

            <label>Gender:</label>
            <input type="radio" id="sgenderMale" name="sgender" value="1">
            <label for="sgenderMale">Male</label>
            <input type="radio" id="sgenderFemale" name="sgender" value="0">
            <label for="sgenderFemale">Female</label>
            <input type="radio" id="sgenderAll" name="sgender" value="2">
            <label for="sgenderAll">All</label>
            <br>

            <input type="submit" value="Search">
        </form>

        <c:if test="${not empty students}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>Actions</th> <!-- Updated column header -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.sid}</td>
                            <td>${student.sname}</td>
                            <td>${student.sgender ? 'Male' : 'Female'}</td>
                            <td>
                                <!-- Add form buttons for each action -->
                                <form action="supdate" method="Get">
                                    <input type="hidden" name="sid" value="${student.sid}">
                                    <input type="submit" value="Update">
                                </form>

                                <form action="sdelete" method="get">
                                    <input type="hidden" name="sid" value="${student.sid}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty students}">
            <p>No students found.</p>
        </c:if>

    </body>
</html>
