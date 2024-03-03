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
        <form action="#" method="get">
            <button type="submit" formaction="Anavi">Perform-Action</button>
        </form>
        <div class="table-container">
            <jsp:include page="../student/list.jsp"></jsp:include>
            <jsp:include page="../teacher/list.jsp"></jsp:include>
            <jsp:include page="../subject/list.jsp"></jsp:include>
            <jsp:include page="../room/list.jsp"></jsp:include>
            <jsp:include page="../timeslot/list.jsp"></jsp:include>
            </div>           
            <div class="table-container">
            <jsp:include page="../session/list.jsp"></jsp:include>
            <jsp:include page="../attendance/list.jsp"></jsp:include>
            <jsp:include page="../assessment/list.jsp"></jsp:include>           
            </div>
            <div class="table-container">
            <jsp:include page="../exam/list.jsp"></jsp:include>            
            <jsp:include page="../grade/list.jsp"></jsp:include> 
            <jsp:include page="../account/list.jsp"></jsp:include> 
        </div>



    </div> </div>
</body>
</html>
