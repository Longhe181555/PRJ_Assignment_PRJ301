<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Perform action on table</title>
    </head>
    <body>
        <form action="ssearch" method="post">
            <button type="submit">Perform action on Students</button>
        </form>
        <form action="tsearch" method="post">
            <button type="submit">Perform action on Teachers</button>
        </form>
        <form action="display" method="post">
            <button type="submit">Back to Display</button>
        </form>
        

    </body>
</html>
