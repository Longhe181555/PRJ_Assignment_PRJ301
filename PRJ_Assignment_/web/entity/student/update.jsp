<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Student</title>
</head>
<body>
    <h2>Update Student</h2>

    <form action="supdate" method="post">
        <label for="sid">Student ID:</label>
        <input type="text" id="sid" name="sid" value="${student.sid}" readonly><br>

        <label for="sname">Student Name:</label>
        <input type="text" id="sname" name="sname" value="${student.sname}" required><br>

        <label>Gender:</label>
        <input type="radio" id="sgenderMale" name="sgender" value="1" ${student.sgender ? 'checked' : ''}>
        <label for="sgenderMale">Male</label>
        <input type="radio" id="sgenderFemale" name="sgender" value="0" ${!student.sgender ? 'checked' : ''}>
        <label for="sgenderFemale">Female</label>
        <br>

        <button type="submit">Update</button>
    </form>

   
</body>
</html>
