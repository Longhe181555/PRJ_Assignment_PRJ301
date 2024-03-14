/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.student;

import controller.authentication.authorization.BaseRBACController;
import dal.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ExamScheduleController extends BaseRBACController {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
           int sid = Integer.parseInt(request.getParameter("id"));
           ExamDBContext dbg = new ExamDBContext();
           ArrayList<Exam> exams = dbg.getBySid(sid);
           request.setAttribute("exams", exams);
                    
         request.getRequestDispatcher("../entity/student/examschedule.jsp").forward(request, response);      
            }catch(NumberFormatException e) {
            request.getSession().setAttribute("loginResult", "SSomething went wrong!, missing Parameter");
            response.sendRedirect(request.getContextPath() +"/login_auth");  
            }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response,Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response,Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
