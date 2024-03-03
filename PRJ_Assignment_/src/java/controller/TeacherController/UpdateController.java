/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.TeacherController;

import dal.TeacherDBContext;
import entity.Teacher;
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
@WebServlet(name="UpdateController", urlPatterns={"/teacherupdate"})
public class UpdateController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
     
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int tid = Integer.parseInt(request.getParameter("tid"));
            TeacherDBContext dbt = new TeacherDBContext();
            Teacher t = dbt.get(tid);
            request.setAttribute("teacher", t);
            request.getRequestDispatcher("entity/teacher/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            int tid = Integer.parseInt(request.getParameter("tid"));
            String tname = request.getParameter("tname");
            String tgmail = request.getParameter("tgmail");

            // Create a Teacher object with updated information
            Teacher updatedTeacher = new Teacher();
            updatedTeacher.setTid(tid);
            updatedTeacher.setTname(tname);
            updatedTeacher.setTgmail(tgmail);

            // Call the update method in TeacherDBContext
            TeacherDBContext teacherDB = new TeacherDBContext();
            teacherDB.update(updatedTeacher);

           
            response.sendRedirect( "tsearch");
        } catch (NumberFormatException ex) {
            response.sendRedirect("tsearch");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
