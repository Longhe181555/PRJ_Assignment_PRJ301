<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
    </style>
    <body>
        <h1><span>FPT University Academic Portal</span> </h1>

        <div id = "nav-container" style="background-color: gray">
            <div id="nav-user" style="float: right;width: 100%;text-align: right; padding: 5px 10px;background-color: lightgrey">
                <a>
                    <a href = "../student?id=${param.id}" class="label label-success" style="text-decoration: none">Back to student page</a> | 
                    <a href="../login_auth" class="label label-success" style="text-decoration: none">Sign out</a> |
                    <span id="user-role" class="label label-success"> Student</span>
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
                            <c:forEach items="${attendances}" var="atts">
                                <c:if test="${atts.session.date eq d and atts.session.timeslot.tsid eq slot.tsid}">
                                    ${atts.session.group.gname} - ${atts.session.group.subject.subname}
                                    <br> Status:
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
