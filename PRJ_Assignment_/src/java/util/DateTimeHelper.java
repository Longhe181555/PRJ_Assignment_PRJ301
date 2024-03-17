/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateTimeHelper {

    public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
        calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek+1);
        return calendar.getTime();
    }

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(utilDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return new java.sql.Date(calendar.getTimeInMillis());
        } else {
            return null;
        }
    }

    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static ArrayList<java.sql.Date> getListBetween(Date from, Date to) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        Date temp = from;
        while (!temp.after(to)) {
            dates.add(convertUtilDateToSqlDate(temp));
            temp = addDaysToDate(temp, 1);
        }
        return dates;
    }

    public static java.util.Date convertSqlDateToUtilDate(java.sql.Date sqlDate) {
        return sqlDate != null ? new java.util.Date(sqlDate.getTime()) : null;
    }

    //Testing per week
    public static int getWeekOfYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static java.sql.Date[] getWeeksForYear(int year) {
        java.sql.Date[] weeks = new java.sql.Date[54]; // Maximum possible number of weeks in a year

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Start from the first day of the year
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        int currentWeek = 1;
        while (calendar.get(Calendar.YEAR) == year) {
            Date weekStart = getWeekStart(calendar.getTime());
            weeks[currentWeek] = new java.sql.Date(weekStart.getTime());
            calendar.add(Calendar.DATE, 7); // Move to the next week
            currentWeek++;
        }

        return weeks;
    }

   
   public static String[] getWeeksForYearString(int year) {
    String[] weeks = new String[54]; // Maximum possible number of weeks in a year

    Calendar calendar = new GregorianCalendar();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, Calendar.JANUARY);
    calendar.set(Calendar.DAY_OF_MONTH, 1); // Start from the first day of the year
    calendar.setFirstDayOfWeek(Calendar.MONDAY);

    int currentWeek = 0; // Starting from 0 to match array index
    while (calendar.get(Calendar.YEAR) == year && currentWeek < 54) {
        Date weekStart = getWeekStart(calendar.getTime());
        Date weekEnd = addDaysToDate(weekStart, 6); // Calculate end of the week
        String weekRange = new SimpleDateFormat("dd/MM").format(weekStart) +
                          " to " +
                          new SimpleDateFormat("dd/MM").format(weekEnd);
        weeks[currentWeek] = weekRange;
        calendar.add(Calendar.DATE, 7); // Move to the next week
        currentWeek++;
    }

    return weeks;
}
    public static java.sql.Date parseFromDate(String dateRange, int year) throws ParseException {
    // Split the date range into start and end dates
    String[] parts = dateRange.split(" to ");

    // Parse the start date using SimpleDateFormat
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date startDate = dateFormat.parse(parts[0].trim() + "/" + year);

    // Convert the java.util.Date to java.sql.Date
    return new java.sql.Date(startDate.getTime());
}
    public static java.sql.Date parseToDate(String dateRange, int year) throws ParseException {
    // Split the date range into start and end dates
    String[] parts = dateRange.split(" to ");

    // Parse the end date using SimpleDateFormat
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date endDate = dateFormat.parse(parts[1].trim() + "/" + year);

    // Convert the java.util.Date to java.sql.Date
    return new java.sql.Date(endDate.getTime());
}
    
}
