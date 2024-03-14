/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.AdminController;

import dal.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DislayLoadedController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String test = "This is a test";
            request.setAttribute("test", test);
            //Load student
            StudentDBContext dbs = new StudentDBContext();
            ArrayList<Student> students = dbs.list();
            int studentsize = 1000;//students.size();
            request.setAttribute("sizestudent", studentsize);
            request.setAttribute("students", students);
            //Load teacher
            TeacherDBContext dbt = new TeacherDBContext();
            ArrayList<Teacher> teachers = dbt.list();
            int teachersize = teachers.size();
            request.setAttribute("sizeteacher", teachersize);
            request.setAttribute("teachers", teachers);
            //Load Subject
            SubjectDBContext dbsub = new SubjectDBContext();
            ArrayList<Subject> subjects = dbsub.list();
            int subjectsize = subjects.size();
            request.setAttribute("sizeSubject", subjectsize);
            request.setAttribute("subjects", subjects);
            //Load Room
            RoomDBContext dbr = new RoomDBContext();
            ArrayList<Room> rooms = dbr.list();
            int roomsize = rooms.size();
            request.setAttribute("sizeRoom", roomsize);
            request.setAttribute("rooms", rooms);
            //Load TimeSlot
            TimeSlotDBContext dbts = new TimeSlotDBContext();
            ArrayList<TimeSlot> timeslots = dbts.list();
            int tssize = timeslots.size();
            request.setAttribute("sizets", tssize);
            request.setAttribute("timeslots", timeslots);
            //Load Session
            SessionDBContext dbsession = new SessionDBContext();
            ArrayList<Session> sessions = dbsession.list();
            int sessionSize = sessions.size();
            request.setAttribute("sizesession", sessionSize);
            request.setAttribute("sessions", sessions);
            //Load Attendance
            AttendanceDBContext dbAttendance = new AttendanceDBContext();
            ArrayList<Attendance> attendances = dbAttendance.list();
            int attendanceSize = attendances.size();
            request.setAttribute("sizeattendance", attendanceSize);
            request.setAttribute("attendances", attendances);
            //Load Group
            GroupDBContext dbGroup = new GroupDBContext();
            ArrayList<Group> groups = dbGroup.list();
            int groupSize = groups.size();
            request.setAttribute("groupSize", groupSize);
            request.setAttribute("groups", groups);
            // Load Assessments
            AssessmentDBContext dbAssessment = new AssessmentDBContext();
            ArrayList<Assessment> assessments = dbAssessment.list();
            int assessmentSize = assessments.size();
            request.setAttribute("sizeassessment", assessmentSize);
            request.setAttribute("assessments", assessments);
            //Load Exam
            ExamDBContext dbExam = new ExamDBContext();
            ArrayList<Exam> exams = dbExam.list();
            int examSize = exams.size();
            request.setAttribute("sizeExam", examSize);
            request.setAttribute("exams", exams);
            //Load Grade
            GradeDBContext dbGrade = new GradeDBContext();
            ArrayList<Grade> grades = dbGrade.list();
            int gradeSize = grades.size();
            request.setAttribute("sizeGrade", gradeSize);
            request.setAttribute("grades", grades);
            //Account
            AccountDBContext dbAccount = new AccountDBContext();
            ArrayList<Account> accounts = dbAccount.list();
            int accountSize = accounts.size();
            request.setAttribute("sizeAccount", accountSize);
            request.setAttribute("accounts", accounts);

            ///view/dislayloaded.jsp
            request.getRequestDispatcher("/entity/admin/displayloaded.jsp").forward(request, response);

            // Use RequestDispatcher to forward the request to the JSP
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/dislayloaded.jsp");
//            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
