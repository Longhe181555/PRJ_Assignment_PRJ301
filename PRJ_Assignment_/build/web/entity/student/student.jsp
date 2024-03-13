<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
        <style>
            a:visited {
                color: #234166; /* or set the color you desire for visited links */
                text-decoration: none; /* Remove underline for visited links */
            }
            .left-box, .right-box {
                padding: 20px;
                width: 400px;
                margin-right: 20px;
                position: relative;
                border-radius: 0px 5px 5px;
                box-shadow: #c4cacc 0px 0px 5px 0px;
            }

            .top-left-corner, .top-right-corner {
                position: absolute;
                width: 100px;
                height: 25px;
                background-color: #eb9316;
                border-radius: 3.9375px;
                box-shadow: #ffffff 0px 1px 0px 0px;
                color: #000;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .navi-list{
                color: #234166;
                font-size: 15px;
                grid-area: auto;
                line-height: 18.5714px;
                margin: 0px 0px 10px;
                padding: 0px 0px 0px 40px;
                text-decoration: none; /* Remove underline */
            }


            .navi-list text {
                color: #23527c;
                display: inline;
                font-size: 15px;
                line-height: 18.5714px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
            }
            .navi-link {
                text-decoration: none; /* Remove underline on hover */
            }
        </style>
    </head>
    <body>
        <div style="display: flex; justify-content: space-evenly">
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
                    <span id="nav-bar" class="label label-success">${studentLogIn.sname}</span></a> | 
                <a href="logout" class="label label-success" style="text-decoration: none">Sign out</a> |
                <span id="user-role" class="label label-success"> Student</span>
            </div>
        </div>

        <div id="container" style="width:100%;padding-top: 30px">

            <br>
            <br>
            <div class="navigation-container" style="display: flex; justify-content: center">
                <div class="left-box">
                    News <button style="background-color:#eb9316;border-radius: 4px;box-shadow: #c4cacc 0px 0px 5px 0px;;color: #ffffff; padding: 6px 12px; border: 0.5px"> View News</button>
                    <img src="img/fpt.png" style="width: 100%; padding: 10px">      
                </div>
                <div class="right-box">
                    <h4 style="color: #234166;
                        font-family: 'Helvetica Neue', sans-serif;
                        font-size: 18px;
                        font-weight: 500;
                        "> Information Access(Tra cứu thông tin)</h4>
                    <ul class="navi-list">
                        <li> <a href="student/timetable?id=${param.id}" class="navi-link">Weekly timetable</a>(Thời khóa biểu từng tuần)</li>
                        <li> <a href="student/exam?id=${param.id}" class="navi-link">View Exam Schedule</a>(Xem lịch thi)</li>
                    </ul>

                </div>



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
                </div>
            </div>
        </div>
    </body>  
</html>
