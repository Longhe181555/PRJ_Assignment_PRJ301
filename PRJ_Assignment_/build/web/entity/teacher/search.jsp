<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Teacher</title>
</head>
<body>
    <form action="display" method="post">
            <button type="submit">Back to Display</button>
        </form>
    
    <h2>Search Teachers</h2>

    <form action="tsearch" method="post">
        <label for="tid">Teacher ID:</label>
        <input type="text" id="tid" name="tid">
        <br>

        <label for="tname">Teacher Name:</label>
        <input type="text" id="tname" name="tname">
        <br>

        <label for="tgmail">Teacher Email:</label>
        <input type="text" id="tgmail" name="tgmail">
        <br>

        <input type="submit" value="Search">
    </form>

    <c:if test="${not empty teachers}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="teacher" items="${teachers}">
                    <tr>
                        <td>${teacher.tid}</td>
                        <td>${teacher.tname}</td>
                        <td>${teacher.tgmail}</td>
                        <td>
                            <form action="teacherupdate" method="get">
                                <input type="hidden" name="tid" value="${teacher.tid}">
                                <button type="submit">Update</button>
                            </form>
                        </td>
                        <td>
                            <form action="tdelete" method="get">
                                <input type="hidden" name="tid" value="${teacher.tid}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
 
    <c:if test="${empty teachers}">
        <p>No teachers found.</p>
    </c:if>
    <form action="tinsert" method="GET">
            <input type="submit" value="Insert">
        </form>

</body>
</html>
