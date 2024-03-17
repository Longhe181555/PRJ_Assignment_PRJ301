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

        body {
            font-size: 16px; /* Set a base font size for the body */
        }

        .container {
            margin:30px  200px;
            align-content: center;

        }

        table {
            border-collapse: collapse;
            width: 100%;
            align-self: center;
            padding: 40px;
        }

        tr.header {
            background-color: #6b90da;
            color: #333333;
            border: 1px solid #000;
        }

        td.header-form {
            padding: 5px;
        }

        td.date-cell {
            border: 1px solid #000;
            padding: 5px;
            font-size: 0.9em; /* Set font size using relative units */
        }
    </style>
    <body>
        <div style="display: flex; justify-content: space-evenly; padding-bottom: 20px">
            <h1><span>FPT University Academic Portal</span> </h1> 
            <div style="display: flex; flex-direction: column">
                <p style="font-weight: bold"> FAP mobile app (myFAP) is ready at </p>
                <div>
                    <tr>
                        <td><a href="https://apps.apple.com/app/id1527723314">
                                <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store" /></a></td>
                        <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store" /></a></td>
                    </tr> 
                </div>
            </div>
        </div>



        <div id = "nav-container" style="background-color: gray;margin: 10px 250px">
            <div id="nav-user" style="float: right;width: 100%;text-align: right; padding: 5px 10px;background-color: lightgrey">
                <a>
                    <a href = "../student?id=${param.id}" class="label label-success" style="text-decoration: none">Back to student page</a> | 
                    <a href="../login_auth" class="label label-success" style="text-decoration: none">Sign out</a> |
                    <span id="user-role" class="label label-success"> Student</span>
            </div>
        </div>
        <br>


        <div class="container">
            <p>
                <b>Note</b>: These activities do not include extra-curricular activities, such as
                club activities ...
            </p>
            <p>
                <b>Note</b>: The activities in the table below do not include extracurricular activities,
                such as club activities ...
            </p>
            <div id="ctl00_mainContent_ghichu">
                <p>
                    Rooms starting with AL are in the Alpha building. Example: AL...<br/>
                    Rooms starting with BE are in the Beta building. Example: BE,..<br/>
                    Rooms starting with G are in the Gamma building. Example: G201,...<br/>
                    Rooms starting with R in the name are in the Vovinam training area.<br/>
                    Rooms starting with DE are in the Delta building. Example: DE,..<br/>
                    Little UK (LUK) is located on the 5th floor of the Delta building.
                </p>
            </div>          



            <table>
                <tr class="header">
                    <td class="header-form">
                        <form action="timetable" method="GET">
                            <input type="hidden" value="${param.id}" name="id"/>
                            Select Week: 
                            <select name="week">
                                <option value="">Select Week</option>
                                <c:forEach var="week" items="${weeks}">
                                    <c:choose>
                                        <c:when test="${week eq param.week}">
                                            <option value="${week}" selected>${week}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${week}">${week}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
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
                        <td style="border: 1px solid #000">Slot: ${slot.tsid}</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td style="border: 1px solid #000;">
                                <c:forEach items="${attendances}" var="atts">
                                    <c:if test="${atts.session.date eq d and atts.session.timeslot.tsid eq slot.tsid}">
                                        <a href="../activitydetails?ssid=${atts.session.ssid}&sid=${param.id}" style="text-decoration: none">
                                            ${atts.session.group.gname} - ${atts.session.group.subject.subname}
                                        </a>
                                        <br>
                                        <div style="background-color: #5cb85c;border-radius:2.4375px; width: 70px;color:white; display:inline; font-weight:700;font-size:9.5px"> 
                                            ${atts.session.timeslot.startHour}:${String.format("%02d", atts.session.timeslot.startMinute)} -
                                            ${atts.session.timeslot.endHour}:${String.format("%02d", atts.session.timeslot.endMinute)}
                                        </div>
                                        <br> Status:
                                        <c:choose>
                                            <c:when test="${atts.isPresent eq 'T'}">
                                                <span style="color: green;">Present</span>
                                            </c:when>
                                            <c:when test="${atts.isPresent eq 'F'}">
                                                <span style="color: red;">Absent</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>Not Yet</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <p style="text-align: center; text-decoration: none">  
            © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
            <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
            <span id="ctl00_lblHelpdesk"></span>

    </body>
</html>
