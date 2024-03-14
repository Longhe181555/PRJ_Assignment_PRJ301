<%-- 
    Document   : activitydetails
    Created on : Mar 13, 2024, 12:20:02 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .container{
            margin: 30px 300px;
        }
        .text {
            color: #333333;
            display: table-cell;
            font-size: 13px;
            grid-area: auto;
            line-height: 18.5714px;
        }


    </style>
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
                <a href="${returnPath}?id=${id}" style="text-decoration: none">
                    <span id="nav-bar" class="label label-success">Return to ${returnPath} page</span></a> | 
                <a href="logout" class="label label-success" style="text-decoration: none">Sign out</a> |
                <span id="user-role" class="label label-success"> ${returnPath} </span>
            </div>
        </div>
        <br> 

        <div class="container">
            <h2> Activity Details</h2>

            <table style="color: #333333; font-size: 13px; line-height: 18.5714px; border-collapse: collapse;">
                <tr>
                    <td>Date:</td>
                    <td>${session.date}</td>
                </tr>
                <tr>
                    <td>Slot:</td>
                    <td>${session.timeslot.tsid}</td>
                </tr>
                <tr>
                    <td>Student group:</td>
                    <td>ID: ${session.group.gid} -Name: ${session.group.gname}</td>
                </tr>
                <tr>
                    <td>Instructor:</td>
                    <td>ID: ${session.teacher.tid} -Name: ${session.teacher.tname}</td>
                </tr>
                <tr>
                    <td>Course:</td>
                    <td>${session.group.subject.subname}</td>
                </tr>
                <tr>
                    <td>Course session number:</td>
                    <td>${session.ssid}</td>
                </tr>
                <tr>
                    <td>Campus/Programme:</td>
                    <td>${session.room.rnumber}</td>
                </tr>
                <tr>
                    <td>Attendance:</td>
                    <td>${session.isTaken}</td>
                </tr>
                <c:if test="${returnPath eq 'student'}">
                    <tr>
                        <td>Record time:</td>
                        <td>${atts.capturedTime}</td>
                    </tr>
                </c:if>
            </table>

        </div>
    </body>
</html>
