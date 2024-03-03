package controller.TeacherController;

import dal.TeacherDBContext;
import entity.Teacher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "SearchTeacher", urlPatterns = {"/tsearch"})
public class SearchController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String rawTid = request.getParameter("tid");
            String tname = request.getParameter("tname");
            String tgmail = request.getParameter("tgmail");

            // Default values if parameters are null
            rawTid = (rawTid == null||rawTid.isEmpty()) ? "0" : rawTid;

            // Parse parameters to integers
            int tid = Integer.parseInt(rawTid);

            // Call the search method in TeacherDBContext
            TeacherDBContext teacherDB = new TeacherDBContext();
            ArrayList<Teacher> teachers = teacherDB.search(tid, tname, tgmail);

            // Forward the results to the JSP for display
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/entity/teacher/search.jsp").forward(request, response);
        
    }
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
