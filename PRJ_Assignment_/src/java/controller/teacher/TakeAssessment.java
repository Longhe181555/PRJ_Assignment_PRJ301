/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.teacher;

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

public class TakeAssessment extends HttpServlet {
  
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
            
            GroupDBContext dbg = new GroupDBContext();
            AssessmentDBContext dbas = new AssessmentDBContext();
        String string_gid = request.getParameter("gid");
        
        if(string_gid!=null&&!string_gid.isEmpty()) {
            int gid = Integer.parseInt(string_gid);
            ArrayList<Assessment> assessments = dbas.listBySubid(dbg.get(gid).getSubject().getSubid());
            System.out.println(assessments.size());
            request.setAttribute("assessments", assessments);
        }
            
        int tid = Integer.parseInt(request.getParameter("id"));
        
        ArrayList<Group> groups = dbg.getByTid(tid);
        request.setAttribute("groups",groups);
        request.getRequestDispatcher("/entity/teacher/takeassessment.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        
    } 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }

   
}
