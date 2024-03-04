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
        <button onclick="window.location.href = '../student?id=${param.id}'">Back to student page</button>
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
                            <c:forEach items="${attendances}" var="atts">
                                <c:if test="${atts.session.date eq d and atts.session.timeslot.tsid eq slot.tsid}">
                                    ${atts.session.group.gname} - ${atts.session.group.subject.subname}
                                    Status:
                                    <c:choose>
                                        <c:when test="${atts.isPresent eq 'T'}">Present</c:when>
                                        <c:when test="${atts.isPresent eq 'F'}">Absent</c:when>
                                        <c:otherwise>Not Yet</c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
