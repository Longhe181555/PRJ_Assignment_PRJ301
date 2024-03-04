<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <button onclick="window.location.href = '../teacher?id=${param.id}'">Back to teacher page</button>
        <form action="timetable" method="GET">
            <input type="hidden" value="${param.id}" name="id"/>
            From: <input type="date" name="from" value="${requestScope.from}"/> -
            <input type="date" name="to" value="${requestScope.to}"/>
            <input type="submit" value="View"/>
        </form>
        <table border="1px">
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>
                        (<fmt:formatDate pattern="E" value="${d}" />)
                        ${d}
                    </td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr>
                    <td>${slot.tsid}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
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

            <table border="1">
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
                        <th>Action</th> <!-- New column for the Update button -->
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
    </body>
</html>
