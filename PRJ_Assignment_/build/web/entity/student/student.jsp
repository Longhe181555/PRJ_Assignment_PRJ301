<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
    </head>
    <body>
        <div>Student Name: ${studentLogIn.sname}</div>
        <button onclick="window.location.href = 'login_auth'">Sign out</button>
        <div class="table">
            <h2>Attendance List</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>SID</th>
                        <th>Present</th>
                        <th>Description</th>
                        <th>Teacher</th>
                        <th>Group Name</th>
                        <th>Subject</th>
                        <th>Date</th>
                        <th>Time Slot</th>
                        <th>Room Number</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="attendance" items="${studentAttendance}">
                        <tr>
                            <td>${attendance.aid}</td>
                            <td>${attendance.isPresent}</td>
                            <td>${attendance.description}</td>
                            <td>${attendance.session.teacher.tname}</td>
                            <td>${attendance.session.group.gname}</td>
                            <td>${attendance.session.group.subject.subname}</td>
                            <td>${attendance.session.date}</td>
                            <td>${attendance.session.timeslot.startHour}:${String.format("%02d", attendance.session.timeslot.startMinute)}-${attendance.session.timeslot.endHour}:${String.format("%02d", attendance.session.timeslot.endMinute)}</td>
                            <td>${attendance.session.room.rnumber}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table">
            <h2>Groups</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Group ID</th>
                        <th>Group Name</th>
                        <th>Subject</th>
                        <th>Teacher (Person in Charge)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="group" items="${ingroup}">
                        <tr>
                            <td>${group.gid}</td>
                            <td>${group.gname}</td>
                            <td>${group.subject.subname}</td>
                            <td>${group.pic.tname}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            <h2>Select Group</h2>
            <form id="GroupSelection" action="student" method="GET"> 
                <input type="hidden" name="id" value="${param.id}" />
                Group <select name="gid" onchange="document.getElementById('GroupSelection').submit()">
                    <option value="0">---- Select Group ----</option>
                    <c:forEach var="group" items="${ingroup}">
                        <option value="${group.gid}">${group.gname} - ${group.subject.subname}</option>
                    </c:forEach>
                </select>
            </form>
        </div>
            <div>
            <jsp:include page="../student/list.jsp"></jsp:include>
            </div>
            
            <div class="table-container">
                <div class="table">
                    <h2>Exam List</h2>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>EID</th>
                                <th>Assessment Name</th>
                                <th>Date</th>
                                <th>TimeSlot</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="exam" items="${exams}">
                            <tr>
                                <td>${exam.eid}</td>
                                <td>${exam.assessment.aname}</td>
                                <td>${exam.date}</td>
                                <td>${exam.timeslot.startHour}:${String.format("%02d", exam.timeslot.startMinute)}-${exam.timeslot.endHour}:${String.format("%02d", exam.timeslot.endMinute)}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="table-container">
                <div class="table">
                    <h2>Grade List</h2>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>GRID</th>
                                <th>Exam ID</th>
                                <th>Subject</th>
                                <th>Student ID</th>
                                <th>Score</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="grade" items="${grades}">
                                <tr>
                                    <td>${grade.grid}</td>
                                    <td>${grade.exam.assessment.aname}</td>
                                    <td>${grade.exam.assessment.subject.subname}</td>
                                    <td>${grade.student.sid}</td>
                                    <td>${grade.score}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                </body>  
                </html>
