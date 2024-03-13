<%-- 
    Document   : dislayLoaded
    Created on : Feb 17, 2024, 2:36:57 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Display all loaded tables</title>
        <style>
            .table-container {
                display: flex;
                gap: 10px; /* Adjust the gap as needed */
            }

            .table {
                width: 30%;
                margin-right: 10px; /* Add some margin between tables */
            }
            .table h2{
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <div class="table-container">
            <jsp:include page="../student/list.jsp"></jsp:include>
            <jsp:include page="../teacher/list.jsp"></jsp:include>
            <div class="table">
                <h2>Subject List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeSubject}</p>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.subid}</td>
                            <td>${subject.subname}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="table">
                <h2>Room List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Number</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeRoom}</p>
                    <c:forEach var="room" items="${rooms}">
                        <tr>
                            <td>${room.rid}</td>
                            <td>${room.rnumber}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
             <div class="table">
                <h2>Time Slot List</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Start Hour</th>
                            <th>Start Minute</th>
                            <th>End Hour</th>
                            <th>End Minute</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizets}</p>
                    <c:forEach var="timeSlot" items="${timeslots}">
                        <tr>
                            <td>${timeSlot.tsid}</td>
                            <td>${timeSlot.startHour}</td>
                            <td>${timeSlot.startMinute}</td>
                            <td>${timeSlot.endHour}</td>
                            <td>${timeSlot.endMinute}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </div>           
            <div class="table-container">
            <div class="table">
                <h2>Session List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Group ID</th>
                            <th>Teacher ID</th>
                            <th>Room ID</th>
                            <th>TimeSlot ID</th>
                            <th>Date</th>
                            <th>Is Taken</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeSession}</p>
                    <c:forEach var="session" items="${sessions}">
                        <tr>
                            <td>${session.ssid}</td>
                            <td>${session.group.gid}</td>
                            <td>${session.teacher.tid}</td>
                            <td>${session.room.rid}</td>
                            <td>${session.timeslot.tsid}</td>
                            <td style="white-space: nowrap;">${session.date}</td>
                            <td>${session.isTaken}</td>
                        </tr>
                    </c:forEach>
             <div class="table">
                    <h2>Attendance List</h2>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Session ID</th>
                                <th>Student ID</th>
                                <th>Present</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                        <p> ${sizeattendance}</p>
                    <c:forEach var="attendance" items="${attendances}">
                        <tr>
                            <td>${attendance.aid}</td>
                            <td>${attendance.session.ssid}</td>
                            <td>${attendance.student.sid}</td>
                            <td>${attendance.isPresent}</td>
                            <td>${attendance.description}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
           <div class="table">
                <h2>Assessment List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Subject ID</th>
                            <th>Name</th>
                            <th>Weight(%)</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p> ${sizeassessment}</p>
                    <c:forEach var="assessment" items="${assessments}">
                        <tr>
                            <td>${assessment.aid}</td>
                            <td>${assessment.subject.subid}</td>
                            <td>${assessment.aname}</td>
                            <td>${assessment.weight}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>       
            </div>
            <div class="table-container">
             <div class="table">
                <h2>Exam List</h2>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Assessment Name</th>
                            <th>Date</th>
                            <th>TimeSlot</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeExam}</p>
                    <c:forEach var="exam" items="${exams}">
                        <tr>
                            <td>${exam.eid}</td>
                            <td>${exam.assessment.aname}</td>
                            <td>${exam.date}</td>
                            <td>${exam.timeslot.startHour}:${String.format("%02d", exam.timeslot.startMinute)} -
                                ${exam.timeslot.endHour}:${String.format("%02d", exam.timeslot.endMinute)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>            
            <div class="table">
                <h2>Grade List</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Exam Name</th>
                            <th>Subject Name</th>
                            <th>Student Name</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                    <p>${sizeGrade}</p>
                    <c:forEach var="grade" items="${grades}">
                        <tr>
                            <td>${grade.grid}</td>
                            <td>${grade.exam.assessment.aname}</td>
                            <td>${grade.exam.assessment.subject.subname}</td>
                            <td>${grade.student.sname}</td>
                            <td>${grade.score}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div> 
            <div class="table">
            <h2>Account List</h2>
            <table border="1">
                
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>ID</th>
     
                </tr>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.username}</td>
                        <td>${account.password}</td>
                        <td>${account.id}</td>

                    </tr>
                </c:forEach>
            </table> 
        </div>



    </div> </div>
</body>
</html>
