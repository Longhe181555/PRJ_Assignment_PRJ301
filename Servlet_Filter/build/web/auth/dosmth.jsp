<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="insert" method="POST">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required><br>

            <label for="publishedDate">Publish Date:</label>
            <input type="date" id="publishedDate" name="publishedDate" required><br>

            <label for="online">Online:</label>
            <input type="checkbox" id="online" name="online"><br>

            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <c:forEach var="cat" items="${cats}">
                    <option value="${cat.catid}">${cat.catname}</option>
                </c:forEach>
            </select><br>

            <input type="submit" value="Save">
        </form>
    </body>
</html>
