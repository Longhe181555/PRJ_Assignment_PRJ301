/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import controller.authentication.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;
import dal.*;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class TakeAttendancesController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles) throws ServletException, IOException {
       try{
        int ssid = Integer.parseInt(request.getParameter("ssid")); // Assuming "ssid" is the session ID
        int tid = Integer.parseInt(request.getParameter("tid"));
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        StudentDBContext studentDB = new StudentDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Student> students = studentDB.getStudentsBySession(ssid);
        ArrayList<Attendance> attendances = new ArrayList<>();
        Session session = new Session();
        session.setSsid(ssid);
        for (Student student : students) {
            Attendance attendance = new Attendance();
            attendance.setSession(session);
            attendance.setStudent(student);
            attendance.setDescription(request.getParameter("description_" + student.getSid()));
            attendance.setIsPresent(request.getParameter("present_" + student.getSid()));
            attendances.add(attendance);
        }
        attendanceDB.takeAttendances(ssid, attendances);
        response.sendRedirect("takeattendance?ssid=" + ssid + "&tid=" + tid);
       }catch (NumberFormatException e) {
           
       }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        int ssid = Integer.parseInt(request.getParameter("ssid"));  // Assuming "id" is the session ID
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        ArrayList<Attendance> attendances = attendanceDB.getAttendanceBySession(ssid);
        request.setAttribute("takeattendance", attendances);
        request.getRequestDispatcher("entity/teacher/takeattendance.jsp").forward(request, response);
    }

   
}
