/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import controller.authentication.authorization.BaseRBACController;
import dal.*;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TakeAttendancesController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        try {
            int ssid = Integer.parseInt(request.getParameter("ssid")); // Assuming "ssid" is the session ID
            int tid = Integer.parseInt(request.getParameter("tid"));
            AttendanceDBContext attendanceDB = new AttendanceDBContext();
            StudentDBContext studentDB = new StudentDBContext();
            ArrayList<Student> students = studentDB.getStudentsBySession(ssid);
            ArrayList<Attendance> attendances = new ArrayList<>();
            Session session = new Session();
            session.setSsid(ssid);
            for (Student student : students) {
                Attendance attendance = new Attendance();
                attendance.setSession(session);
                attendance.setStudent(student);
                attendance.setDescription(request.getParameter("description_" + student.getSid()));
                attendance.setIsPresent(request.getParameter("present_" + student.getSid()));
                attendances.add(attendance);
            }
            for (Student student : students) {
                if (attendanceDB.checkAttendanceThreshold(student.getSid(), tid)) {
                    sendMail(request,response);
                }
            }
            attendanceDB.takeAttendances(ssid, attendances);
            response.sendRedirect("takeattendance?ssid=" + ssid + "&tid=" + tid);
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        int ssid = Integer.parseInt(request.getParameter("ssid"));  // Assuming "id" is the session ID
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        ArrayList<Attendance> attendances = attendanceDB.getAttendanceBySession(ssid);
        request.setAttribute("takeattendance", attendances);
        request.getRequestDispatcher("entity/teacher/takeattendance.jsp").forward(request, response);
    }
    String email = "testerstudentdvd@gmail.com";

    protected void sendMail(HttpServletRequest request, HttpServletResponse response) {
        final String username = "testerstudentdvd@gmail.com";//your email id
        final String password = "sqzeejvbutblzwhq";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            MimeBodyPart textPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            String final_Text = """
                                    You have absent too much
                                    """;
            textPart.setText(final_Text);
            multipart.addBodyPart(textPart);
            message.setContent(multipart, "text/html; charset=utf-8");
            message.setSubject("Absent too much");
            Transport.send(message);
          
        } catch (Exception e) {

        }
    }
}
