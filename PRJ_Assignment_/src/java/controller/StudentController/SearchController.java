/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.StudentController;

import dal.StudentDBContext;
import entity.Student;
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
public class SearchController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String rawSid = request.getParameter("sid");
            String sname = request.getParameter("sname");
            String rawSgender = request.getParameter("sgender");

            // Default values if parameters are null
            rawSid = (rawSid == null||rawSid.isEmpty()) ? "0" : rawSid;
            rawSgender = (rawSgender == null||rawSgender.isEmpty()) ? "2" : rawSgender;

            // Parse parameters to integers
            int sid = Integer.parseInt(rawSid);
            int sgender = Integer.parseInt(rawSgender);

            // Call the search method in StudentDBContext
            StudentDBContext studentDB = new StudentDBContext();
            ArrayList<Student> students = studentDB.search(sid, sname, sgender);

            // Forward the results to the JSP for display
            request.setAttribute("students", students);
            request.getRequestDispatcher("/entity/student/search.jsp").forward(request, response);
        
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        // Show the initial search form
         processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);
    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
