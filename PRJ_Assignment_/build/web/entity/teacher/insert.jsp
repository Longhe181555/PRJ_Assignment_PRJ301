<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert Teacher</title>
</head>
<body>
    <h2>Insert Teacher</h2>
    
    <form action="tinsert" method="post">
        <label for="teacherName">Teacher Name:</label>
        <input type="text" id="tname" name="tname" required/><br/>

        <label for="teacherEmail">Teacher Email:</label>
        <input type="text" id="tgmail" name="tgmail" required/><br/>

        <button type="submit">Insert</button>
    </form>

    <br/>

    <a href="tsearch">Back to teacher search</a>
</body>
</html>
