package controller.StudentController;

import dal.StudentDBContext;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InsertStudent", urlPatterns = {"/sinsert"})
public class InsertController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/entity/student/insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");

        // Retrieve data from the form
        String studentName = request.getParameter("studentName");
        boolean studentGender = Boolean.parseBoolean(request.getParameter("studentGender"));

        Student student = new Student();
        student.setSname(studentName);
        student.setSgender(studentGender);

        StudentDBContext studentDBContext = new StudentDBContext();
        studentDBContext.insert(student);

        // Redirect back to the student list page
        response.sendRedirect("display");
    }

    @Override
    public String getServletInfo() {
        return "Insert Student Controller";
    }
}
