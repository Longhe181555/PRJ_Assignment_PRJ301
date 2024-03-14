/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import entity.*;
import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MarkReportController", urlPatterns = {"/MarkReportController"})
public class MarkReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SubjectDBContext dbs = new SubjectDBContext();
            GradeDBContext dbg = new GradeDBContext();
            
            int id = Integer.parseInt(request.getParameter("id"));
            Integer subid = null; // Initialize subid as null

            // Check if subid parameter is present in the request
            String subidParam = request.getParameter("subid");
            if (subidParam != null && !subidParam.isEmpty()) {
                subid = Integer.parseInt(subidParam);
                ArrayList<Grade> grades = dbg.getBySubidAndSid(subid, id);
                request.setAttribute("grades", grades);
            }

            // Handle loading additional table to see grade if subid is present
            if (subid != null) {
                // Handle loading additional table based on subid
                // Your code to handle loading based on subid goes here
            }

            ArrayList<Subject> s = dbs.enrolledSubjects(id);
            request.setAttribute("subjects", s);

            request.getRequestDispatcher("/entity/student/markreport.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("loginResult", "SSomething went wrong!, missing Parameter");
            response.sendRedirect(request.getContextPath() +"/login_auth");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
