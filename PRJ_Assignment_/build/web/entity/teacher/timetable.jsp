<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            align-self: center;
            margin: 30px;
        }

        tr.header {
            background-color: #6b90da;
            color: #333333;
            border: 1px solid #000;
        }

        td.header-form {
            padding: 5px; /* Adjust the padding as needed */
        }

        td.date-cell {
            border: 1px solid #000;
            padding: 5px; /* Adjust the padding as needed */
        }

        div.table {
            margin: 20px;
        }

        table.session-table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        table.session-table th, table.session-table td {
            border: 1px solid #000;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #6b90da;
            color: #333333;
        }
    </style>
</head>
<body>

<h1><span>FPT University Academic Portal</span> </h1>

<div id="nav-container" style="background-color: gray">
    <div id="nav-user" style="float: right;width: 100%;text-align: right; padding: 5px 10px;background-color: lightgrey">
        <a href="../teacher?id=${param.id}" class="label label-success" style="text-decoration: none">Back to teacher page</a> |
        <a href="../login_auth" class="label label-success" style="text-decoration: none">Sign out</a> |
        <span id="user-role" class="label label-success"> Teacher</span>
    </div>
</div>

<table>
    <tr class="header">
        <td class="header-form">
            <form action="timetable" method="GET">
                <input type="hidden" value="${param.id}" name="id"/>
                From: <input type="date" name="from" value="${requestScope.from}"/> <br>
                To: <input type="date" name="to" value="${requestScope.to}"/> <br>
                <input type="submit" value="View"/>
            </form>
        </td>
        <c:forEach items="${requestScope.dates}" var="d">
            <td class="date-cell">
                (<fmt:formatDate pattern="E" value="${d}" />)
                ${d}
            </td>
        </c:forEach>
    </tr>
    <c:forEach items="${requestScope.slots}" var="slot">
        <tr>
            <td style="border: 1px solid #000;">Slot: ${slot.tsid}</td>
            <c:forEach items="${requestScope.dates}" var="d">
                <td style="border: 1px solid #000;">
                    <c:forEach items="${requestScope.sessions}" var="session">
                        <c:if test="${session.date eq d and session.timeslot.tsid eq slot.tsid}">
                            ${session.group.gname} - ${session.group.subject.subname}
                            <form action="../takeattendance" method="GET">
                                <input type="hidden" name="ssid" value="${session.ssid}" />
                                <input type="hidden" name="tid" value="${session.teacher.tid}" />
                                <button type="submit">
                                    <c:choose>
                                        <c:when test="${session.isTaken eq 'T'}">Edit Attendance</c:when>
                                        <c:otherwise>Take Attendance</c:otherwise>
                                    </c:choose>
                                </button>
                            </form>
                        </c:if>
                    </c:forEach>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>

<div class="table">
    <h2>Session List</h2>
    <table class="session-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Group ID</th>
            <th>Teacher</th>
            <th>Room Number</th>
            <th>Time Slot</th>
            <th>Date</th>
            <th>Is Taken</th>
            <th>Group Name</th>
            <th>Subject</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="session" items="${sessions}">
            <tr>
                <td>${session.ssid}</td>
                <td>${session.group.gid}</td>
                <td>${session.teacher.tname}</td>
                <td>${session.room.rnumber}</td>
                <td>${session.timeslot.startHour} :
                    ${String.format("%02d", session.timeslot.startMinute)}-${session.timeslot.endHour} :
                    ${String.format("%02d", session.timeslot.endMinute)}</td>
                <td style="white-space: nowrap;">${session.date}</td>
                <td>${session.isTaken}</td>
                <td>${session.group.gname}</td>
                <td>${session.group.subject.subname}</td>
                <td>
                    <form action="../takeattendance" method="GET">
                        <input type="hidden" name="tid" value="${session.teacher.tid}" />
                        <input type="hidden" name="ssid" value="${session.ssid}" />
                        <button type="submit">
                            <c:choose>
                                <c:when test="${session.isTaken eq 'T'}">Edit Attendance</c:when>
                                <c:otherwise>Take Attendance</c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

                <p style="text-align: center; text-decoration: none">  
            © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
            <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
            <span id="ctl00_lblHelpdesk"></span>
                
</body>
</html>
