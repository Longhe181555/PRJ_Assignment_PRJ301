<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Insert Student</title>
    </head>
    <body>
        <h2>Insert Student</h2>
        <form action="sinsert" method="post">
            <label for="studentName">Student Name:</label>
            <input type="text" id="studentName" name="studentName" required/><br/>

            <label>Gender:</label>
            <input type="radio" id="male" name="studentGender" value="true" required>
            <label for="male">Male</label>

            <input type="radio" id="female" name="studentGender" value="false" required>
            <label for="female">Female</label><br/>

            <button type="submit">Insert</button>
        </form>


        <br/>

        <a href="ssearch">Back to student search</a>
    </body>
</html>
