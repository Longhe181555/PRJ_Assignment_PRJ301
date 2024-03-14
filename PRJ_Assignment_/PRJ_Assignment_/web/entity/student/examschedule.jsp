<%-- 
    Document   : examschedule
    Created on : Mar 13, 2024, 4:36:42 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-size: 16px; /* Set a base font size for the body */
            }

            .container {
                margin:30px  200px;
                align-content: center;

            }
            .table-container {
                align-content: center;
                margin:10px 200px;

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
    </head>
    <body>
        <div>
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
                        <a href = "../student?id=${param.id}" class="label label-success" style="text-decoration: none">Back to student page</a> | 
                        <a href="../login_auth" class="label label-success" style="text-decoration: none">Sign out</a> |
                        <span id="user-role" class="label label-success"> Student</span>
                </div>
            </div> 
            <br>
            
            
        </div>
        <h3 style="text-align: center;margin-top:40px"> Exam Schedules </h3>
        
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
                
                
        <p style="text-align: center; text-decoration: none">  
            © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
            <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
            <span id="ctl00_lblHelpdesk"></span>

    </body>
</html>
