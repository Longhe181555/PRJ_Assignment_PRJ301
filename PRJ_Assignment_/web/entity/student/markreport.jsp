<%-- 
    Document   : grade
    Created on : Mar 13, 2024, 5:16:22 PM
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
                display:flex;
                
            }
            table {
                width: 300px;
                align-self: center;
                padding: 40px;


            }

            tr.header {
                background-color: #6b90da;
                color: white;
                border: 1px solid #000;
            }

            .table tbody tr td {

                text-align: center;

            }
            .table td {
                border: 1px solid black; /* Add a border to each cell */

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
            <h3 style="text-align: center;margin-top:40px"> Exam Schedules </h3>

            <div class="table-container">
                <div class="table">
                    <table>
                        <thead class="header-form">
                            <tr class="header">
                                <th>Course ID</th>
                                <th>Course Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="subject" items="${subjects}">
                                <tr>
                                    <td>${subject.subid}</td>
                                    <td >
                                        <a href="markreport?subid=${subject.subid}&id=${param.id}" style="text-decoration: none;">${subject.subname}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="table">
                <c:if test="${not empty param.subid}">
                    <table >
                        <thead class="header-form">
                            <tr class="header">
                                <th>Assessment Name</th>
                                <th>Score</th>
                                <!-- Add more headers as needed -->
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="grade" items="${grades}">
                                <tr>
                                    <td>${grade.assessment.aname}</td>
                                    <td>${grade.score}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            </div>
            <p style="text-align: center; text-decoration: none">  
                © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
                <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
                <span id="ctl00_lblHelpdesk"></span>
                </body>
                </html>
