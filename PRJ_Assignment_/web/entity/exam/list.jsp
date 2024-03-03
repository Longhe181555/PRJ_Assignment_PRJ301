<%-- 
    Document   : list
    Created on : Feb 28, 2024, 11:42:56 AM
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
    <body>
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
    </body>
</html>
