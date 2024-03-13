<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
     <style>
            .nav-container{
                margin: 50px;
            }
            .container {
                margin:40px  200px;
                align-content: center;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                align-self: center;
        
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
                <a href="teacher/timetable?id=${param.tid}" style="text-decoration: none">
                    <span id="nav-bar" class="label label-success">Return to time table </span></a> | 
                <a href="logout" class="label label-success" style="text-decoration: none">Sign out</a> |
                <span id="user-role" class="label label-success"> Teacher</span>
            </div>
        </div>
       <br> 
    
    
    
       <div class = "container">
           <form action="takeattendance" method="post">
        <table border="1">
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Is Present</th>
                <th>Description</th> <!-- New column for the text box -->
            </tr>
            <c:forEach var="attendance" items="${takeattendance}">
                <tr>
                    <td>${attendance.student.sid}</td>
                    <td>${attendance.student.sname}</td>
                    <td>
                        <input type="radio" name="present_${attendance.student.sid}" value="T" ${attendance.isPresent eq 'T' ? 'checked' : ''}> Present
                        <input type="radio" name="present_${attendance.student.sid}" value="F" ${attendance.isPresent eq 'F' ? 'checked' : ''}> Absent
                    </td>
                    <td>
                        <!-- Add a description text box with a unique name -->
                        <input type="text" name="description_${attendance.student.sid}" value="${attendance.description}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <input type="submit" value="Save Attendance">
        <input type="hidden" name="ssid" value="${param.ssid}">
        <input type="hidden" name="tid" value="${param.tid}">
           
       </div>
    
    </form>
    
    <p style="text-align: center; text-decoration: none">  
            © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
            <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
            <span id="ctl00_lblHelpdesk"></span>
</body>
</html>
