/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class AttendanceReportController extends HttpServlet {

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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupDBContext dbg = new GroupDBContext();
        AttendanceDBContext dba = new AttendanceDBContext();
        int sid = Integer.parseInt(request.getParameter("id"));
        String string_gid = request.getParameter("gid");

        ArrayList<Group> groups = dbg.getBySid(sid);
        request.setAttribute("groups", groups);
        if (string_gid != null && !string_gid.isEmpty()) {
            try {
                int gid = Integer.parseInt(string_gid);

                ArrayList<Attendance> atts = dba.getBySidAndGid(sid, gid);
                request.setAttribute("selected_group", dbg.get(gid));
                request.setAttribute("atts", atts);

                int absentCount = 0;
                for (Attendance att : atts) {
                    if ("F".equals(att.getIsPresent())) {
                        absentCount++;
                    }
                }
                request.setAttribute("absentCount", absentCount);
            } catch (NumberFormatException e) {
            }

        }
        request.getRequestDispatcher("/entity/student/attendancereport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
