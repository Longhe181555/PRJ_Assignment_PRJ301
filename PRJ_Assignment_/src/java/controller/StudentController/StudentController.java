/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.StudentController;

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
@WebServlet(name = "StudentController", urlPatterns = {"/student"})
public class StudentController extends BaseRequiredAuthenticationController {

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
        GroupDBContext dbg = new GroupDBContext();
        StudentDBContext dbs = new StudentDBContext();
        Student s = dbs.get(enteredId);
        ArrayList<Group> ingroup = dbg.getBySid(enteredId);
        request.setAttribute("ingroup", ingroup);
        request.setAttribute("studentLogIn", s);
        int groupId = 0;
        try {
            groupId = Integer.parseInt(request.getParameter("gid"));
        } catch (Exception e) {
        }

        if (groupId != 0) {
            ArrayList<Student> samegroup = dbs.getByGid(groupId);
            request.setAttribute("students", samegroup);
        }

        //load Attendances
        AttendanceDBContext dba = new AttendanceDBContext();
        ArrayList<Attendance> attendances = dba.getBySid(enteredId);
        request.setAttribute("studentAttendance", attendances);

        //Load Exam student has to take
        ExamDBContext dbExam = new ExamDBContext();
        ArrayList<Exam> exams = dbExam.getBySid(enteredId);
        request.setAttribute("exams", exams);
        //Load Grade of Student
        GradeDBContext dbGrade = new GradeDBContext();
        ArrayList<Grade> grades = dbGrade.getGrade(enteredId); // Assuming you have the studentId
        int gradeSize = grades.size();
        request.setAttribute("grades", grades);

        RequestDispatcher dispatcher = request.getRequestDispatcher("entity/student/student.jsp");
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
