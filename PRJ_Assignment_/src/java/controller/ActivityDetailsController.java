/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ActivityDetailsController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        int ssid = 0;
        AttendanceDBContext dba = new AttendanceDBContext();
        String returnPath = request.getContextPath() + "/login_auth";
        if (request.getParameter("tid") != null) {
            returnPath = "teacher";
            id = Integer.parseInt(request.getParameter("tid"));
        } else if (request.getParameter("sid") != null) {
            returnPath = "student";
            id = Integer.parseInt(request.getParameter("sid"));
            Attendance a = dba.getBySsidandSid(ssid, id);
            request.setAttribute("a",a);
        }

        try {
             ssid = Integer.parseInt(request.getParameter("ssid"));
            SessionDBContext dbs = new SessionDBContext();
            Session session = dbs.get(ssid);
            request.setAttribute("session", session);
            
        } catch (NumberFormatException e) {

        }
        request.setAttribute("returnPath", returnPath);
        request.setAttribute("id", id);
        request.getRequestDispatcher("/view/activitydetails.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
