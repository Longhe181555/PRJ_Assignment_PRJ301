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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TeacherController", urlPatterns = {"/teacher"})
public class TeacherController extends BaseRequiredAuthenticationController {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        int enteredId = Integer.parseInt(request.getParameter("id"));
        int selectedGroupId = -1;
        try{
         selectedGroupId = Integer.parseInt(request.getParameter("gid"));
        } catch(NumberFormatException e) { }        
//Get teacher
        TeacherDBContext dbs = new TeacherDBContext();
        Teacher t = dbs.get(enteredId);
        request.setAttribute("teacherLogIn", t);
        //get Session that the teacher in charge/teach
        SessionDBContext dbss = new SessionDBContext();
        ArrayList<Session> sessionInCharge = dbss.listByTeacherId(enteredId);
        request.setAttribute("sessionInCharge", sessionInCharge);
        //Load Subject
        SubjectDBContext dbsub = new SubjectDBContext();
        ArrayList<Subject> subjects = dbsub.list();
        request.setAttribute("subjects", subjects);
        //Load Group that the teacher in charge of
        GroupDBContext groupDBContext = new GroupDBContext();
        ArrayList<Group> groupsByTid = groupDBContext.getByTid(enteredId);
        request.setAttribute("groupsByTid", groupsByTid);
        //Load Student that the student in charge off
        if (selectedGroupId != -1) {
        StudentDBContext studentDBContext = new StudentDBContext();
        ArrayList<Student> studentsByGroup = studentDBContext.getByGid(selectedGroupId);
        request.setAttribute("students", studentsByGroup);
    }
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("entity/teacher/teacher.jsp");
        dispatcher.forward(request, response);

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
