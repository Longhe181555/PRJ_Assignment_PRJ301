<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher</title>
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
                    <span id="nav-bar" class="label label-success">${teacherLogIn.tname}</span></a> | 
                <a href="logout" class="label label-success" style="text-decoration: none">Sign out</a> |
                <span id="user-role" class="label label-success"> Student</span>
            </div>
        </div>
       <br> 
       
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
                        <li> <a href="teacher/timetable?id=${param.id}" class="navi-link">Weekly timetable</a>(Thời khóa biểu từng tuần)</li>
                        <li> <a href="teacher/assessment?id=${param.id}" class="navi-link">Check Assessment</a>(Take Assessment)</li>
                    </ul>

                </div>
            </div>
        <br>
        <!-- Add this section to display groupsByTid -->
        <div class="table">
            <h2>Groups Assigned to Teacher</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Subject</th>
                    </tr>
                </thead>
                <tbody>
                <p>${sizeGroupsByTid}</p>
                <c:forEach var="group" items="${groupsByTid}">
                    <tr>
                        <td>${group.gid}</td>
                        <td>${group.gname}</td>
                        <td>${group.subject.subname}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
                <br>
        <div>
            <h2>Select Group:</h2>
            <form action="teacher" method="GET">
                <input type="hidden" name="id" value="${param.id}" />
                Group 
                <select name="gid" onchange="this.form.submit()">
                    <option value="-1">---- Select Group ----</option>
                    <c:forEach var="group" items="${groupsByTid}">
                        <option value="${group.gid}">${group.gname} - ${group.subject.subname}</option>
                    </c:forEach>
                </select>
            </form>
        </div>
                <div>
                    <h2>Students in charge of</h2>
                <jsp:include page="../student/list.jsp"></jsp:include>
               </div>
               
               <p style="text-align: center; text-decoration: none">  
            © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
            <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
            <span id="ctl00_lblHelpdesk"></span>
    </body>
</html>
