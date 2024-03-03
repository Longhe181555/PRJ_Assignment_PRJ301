/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.*;
import entity.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TeacherController", urlPatterns = {"/attendance"})
public class TakeAttendanceController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @param account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        int tid = Integer.parseInt(request.getParameter("tid"));
        int ssid = Integer.parseInt(request.getParameter("ssid"));
        AttendanceDBContext dba = new AttendanceDBContext();
        ArrayList<Attendance> takeAttendance = dba.getAttendanceByTeacherAndSession(tid, ssid);
        request.setAttribute("takeattendance", takeAttendance);
        request.setAttribute("testsize", takeAttendance.size());
        request.setAttribute("tid", tid);
        request.setAttribute("ssid", ssid);
        request.getRequestDispatcher("entity/teacher/takeattendance.jsp").forward(request, response);
        

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("tid"));
        int sessionId = Integer.parseInt(request.getParameter("ssid"));

        // Process attendance update logic
        AttendanceDBContext attendanceDB = new AttendanceDBContext();

        // Retrieve all parameter names
        Map<String, String[]> parameterMap = request.getParameterMap();

        for (String paramName : parameterMap.keySet()) {
            // Check if the parameter name starts with "isPresent_"
            if (paramName.startsWith("isPresent_")) {
                // Extract attendance ID from the parameter name
                int aid = Integer.parseInt(paramName.substring("isPresent_".length()));
                // Get the value of isPresent from the parameter
                String isPresent = request.getParameter(paramName);
                // Get the value of description from the parameter
                String description = request.getParameter("description_" + aid);

                // Update the attendance status and description in the database
                attendanceDB.updateAttendances(aid, isPresent, description);
            }
        }

        SessionDBContext sessionDB = new SessionDBContext();
        sessionDB.updateIsTakenStatus(sessionId, "T");

        // Redirect back to the attendance table page
        response.sendRedirect("attendance?tid=" + teacherId + "&ssid=" + sessionId);
    }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   


