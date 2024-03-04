<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher</title>
    </head>
    <body>
        <div>Teacher Name: ${teacherLogIn.tname}</div>
        <button onclick="window.location.href = 'login_auth'">Sign out</button>
        
        
        <!-- Add this section to display groupsByTid -->
        <div class="table">
            <h2>Groups Assigned to Teacher</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Subject</th>
                    </tr>
                </thead>
                <tbody>
                <p>${sizeGroupsByTid}</p>
                <c:forEach var="group" items="${groupsByTid}">
                    <tr>
                        <td>${group.gid}</td>
                        <td>${group.gname}</td>
                        <td>${group.subject.subname}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
                <br>
                 <button onclick="window.location.href = './teacher/timetable?id=${param.id}'">View Time Table</button>
        <div>
            <h2>Select Group:</h2>
            <form action="teacher" method="GET">
                <input type="hidden" name="id" value="${param.id}" />
                Group 
                <select name="gid" onchange="this.form.submit()">
                    <option value="-1">---- Select Group ----</option>
                    <c:forEach var="group" items="${groupsByTid}">
                        <option value="${group.gid}">${group.gname} - ${group.subject.subname}</option>
                    </c:forEach>
                </select>
            </form>
        </div>
                <div>
                    <h2>Students in charge of</h2>
                <jsp:include page="../student/list.jsp"></jsp:include>
               </div>
    </body>
</html>
