/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.*;
import entity.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class GradingController extends HttpServlet {
   
   
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        GradeDBContext dbg = new GradeDBContext();
      String string_gid = request.getParameter("gid");
      String string_aid = request.getParameter("aid");
      try{
      int gid = Integer.parseInt(string_gid);
      int aid = Integer.parseInt(string_aid);
      
      ArrayList<Grade> grades = dbg.getByGidAndAid(gid, aid);
      request.setAttribute("grades", grades);
      request.setAttribute("eid", grades.get(1).getExam().getEid());
      request.getRequestDispatcher("/entity/teacher/grading.jsp").forward(request, response);
      
      } catch(NumberFormatException e) {
      }
      
    } 

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve the exam ID and group ID from the request parameters
    GradeDBContext dbg = new GradeDBContext();
    StudentDBContext dbs = new StudentDBContext();
    StudentDBContext dbst = new StudentDBContext();
    int aid = Integer.parseInt(request.getParameter("aid"));
    int eid = Integer.parseInt(request.getParameter("eid"));
    int gid = Integer.parseInt(request.getParameter("gid"));
    
    ArrayList<Student> students = dbst.getByGid(gid);
    ArrayList<Grade> grades = new ArrayList<>();
    for(Student s : students) {
        
            Grade grade = new Grade();
            grade.setStudent(s); // Assuming you have a constructor for Student that accepts sid
            grade.setScore(Integer.parseInt(request.getParameter("grades_" + s.getSid())));
            grades.add(grade);
    }
    
    // Call the method to handle grading
    dbg.takeGrades(eid, gid, grades);
    String id  = request.getParameter("id");
    // Redirect or forward to a success page
    response.sendRedirect("grading?gid=" + gid + "&aid=" + aid + "$id=" + id);
}

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
