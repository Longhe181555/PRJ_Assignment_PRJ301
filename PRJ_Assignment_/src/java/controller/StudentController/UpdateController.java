package controller.StudentController;

import dal.StudentDBContext;
import entity.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateStudentController", urlPatterns = {"/supdate"})
public class UpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the form for updating a student
        int sid = Integer.parseInt(request.getParameter("sid"));
        StudentDBContext studentDB = new StudentDBContext();
        Student student = studentDB.get(sid);

        // Set student attribute to be used in the JSP
        request.setAttribute("student", student);
        request.getRequestDispatcher("/entity/student/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            int sid = Integer.parseInt(request.getParameter("sid"));
            String sname = request.getParameter("sname");
            int sgender = Integer.parseInt(request.getParameter("sgender"));

            // Create a Student object with updated information
            Student updatedStudent = new Student();
            updatedStudent.setSid(sid);
            updatedStudent.setSname(sname);
            updatedStudent.setSgender(sgender == 1); // Convert to boolean

            // Call the update method in StudentDBContext
            StudentDBContext studentDB = new StudentDBContext();
            studentDB.update(updatedStudent);

            // Redirect to the search page after a successful update
            response.sendRedirect(request.getContextPath() + "/ssearch");

        } catch (NumberFormatException ex) {
            // Handle the case where sid or sgender is not a valid number
            response.sendRedirect(request.getContextPath() + "/ssearch");
        }
    }
}
