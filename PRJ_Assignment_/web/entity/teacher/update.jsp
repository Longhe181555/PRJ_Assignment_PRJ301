<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Teacher</title>
</head>
<body>
    <h2>Update Teacher</h2>

    <form action="teacherupdate" method="post">
        <label for="tid">Teacher ID:</label>
        <input type="text" id="tid" name="tid" value="${teacher.tid}" readonly><br>

        <label for="tname">Teacher Name:</label>
        <input type="text" id="tname" name="tname" value="${teacher.tname}" required><br>

        <label for="tgmail">Teacher Email:</label>
        <input type="text" id="tgmail" name="tgmail" value="${teacher.tgmail}" required><br>

        <button type="submit">Update</button>
    </form>

    <a href="tsearch">Back to teacher search</a>
</body>
</html>
