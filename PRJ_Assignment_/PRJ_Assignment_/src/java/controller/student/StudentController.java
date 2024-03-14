/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.authorization.BaseRBACController;
import dal.*;
import entity.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StudentController", urlPatterns = {"/student"})
public class StudentController extends BaseRBACController {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
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

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
            throws ServletException, IOException {
        
    }

    
   
}
