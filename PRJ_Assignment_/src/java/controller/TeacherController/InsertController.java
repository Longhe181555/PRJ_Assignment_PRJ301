package controller.TeacherController;

import dal.TeacherDBContext;
import entity.Teacher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TeacherInsertController", urlPatterns = {"/tinsert"})
public class InsertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/entity/teacher/insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            String teacherName = request.getParameter("tname");
            String teacherEmail = request.getParameter("tgmail");

            // Create a Teacher object
            Teacher newTeacher = new Teacher();
            newTeacher.setTname(teacherName);
            newTeacher.setTgmail(teacherEmail);

            // Call the insert method in TeacherDBContext
            TeacherDBContext teacherDB = new TeacherDBContext();
            teacherDB.insert(newTeacher);

            // Redirect to the search page after successful insert
            response.sendRedirect(request.getContextPath() + "/tsearch");

        } catch (Exception ex) {
            // Handle exceptions if needed
            response.sendRedirect(request.getContextPath() + "/tsearch");
        }
    }
}
