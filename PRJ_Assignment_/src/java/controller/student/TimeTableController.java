/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.authorization.BaseRBACController;
import dal.*;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateTimeHelper;

public class TimeTableController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        int tid = Integer.parseInt(request.getParameter("id"));
        String raw_week = request.getParameter("week");
        java.sql.Date from = null;
        java.sql.Date to = null;

        Date today = new Date();
        if(raw_week==null) {
            from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.getWeekStart(today));
            to = DateTimeHelper.convertUtilDateToSqlDate(
                    DateTimeHelper.addDaysToDate(DateTimeHelper.getWeekStart(today), 6));
        } else {
            String selectedWeek = java.net.URLDecoder.decode(raw_week);
            try {
                from = DateTimeHelper.parseFromDate(selectedWeek,2024);
            } catch (ParseException ex) {
                Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                to = DateTimeHelper.parseToDate(selectedWeek,2024);
            } catch (ParseException ex) {
                Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Test
        String[] weeks = DateTimeHelper.getWeeksForYearString(2024);
        request.setAttribute("weeks", weeks);       
 
        ArrayList<java.sql.Date> dates = DateTimeHelper.getListBetween(
                DateTimeHelper.convertSqlDateToUtilDate(from),
                DateTimeHelper.convertSqlDateToUtilDate(to));

        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();

        SessionDBContext sessDB = new SessionDBContext();

        String selectedWeek = DateTimeHelper.getWeekRangeAsString(from);
        request.setAttribute("selectedWeek", selectedWeek);
        
        
        ArrayList<Attendance> attendances = sessDB.getBySid(tid, from, to);
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("attendances", attendances);

        request.getRequestDispatcher("../entity/student/timetable.jsp").forward(request, response);
    }

}
