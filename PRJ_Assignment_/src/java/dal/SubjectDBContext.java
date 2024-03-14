/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Subject;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubjectDBContext extends DBContext<Subject> {

     @Override
    public ArrayList<Subject> list() {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT subid,subname FROM Subject";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubid(rs.getInt("subid"));
                subject.setSubname(rs.getString("subname"));
                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    @Override
    public void insert(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
    try {
        String sql = "SELECT subid, subname FROM Subject WHERE subid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Subject subject = new Subject();
            subject.setSubid(rs.getInt("subid"));
            subject.setSubname(rs.getString("subname"));
            return subject;
        }
    } catch (SQLException ex) {
        Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}
   public ArrayList<Subject> enrolledSubjects(int studentId) {
    ArrayList<Subject> subjects = new ArrayList<>();
    try {
        String sql = "SELECT DISTINCT Subject.subid, Subject.subname "
                   + "FROM Student "
                   + "JOIN Attendance ON Student.sid = Attendance.sid "
                   + "JOIN Session ON Attendance.ssid = Session.ssid "
                   + "JOIN [Group] ON Session.gid = [Group].gid "
                   + "JOIN Subject ON [Group].subid = Subject.subid "
                   + "WHERE Student.sid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, studentId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Subject subject = new Subject();
            subject.setSubid(rs.getInt("subid"));
            subject.setSubname(rs.getString("subname"));
            subjects.add(subject);
        }
    } catch (SQLException ex) {
        Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return subjects;
}
public ArrayList<Subject> teachSubjects(int teacherId) {
    ArrayList<Subject> subjects = new ArrayList<>();
    try {
        String sql = "SELECT DISTINCT Subject.subid, Subject.subname "
                   + "FROM Teacher "
                   + "JOIN [Group] ON Teacher.tid = [Group].pic "
                   + "JOIN Subject ON [Group].subid = Subject.subid "
                   + "WHERE Teacher.tid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, teacherId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Subject subject = new Subject();
            subject.setSubid(rs.getInt("subid"));
            subject.setSubname(rs.getString("subname"));
            subjects.add(subject);
        }
    } catch (SQLException ex) {
        Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return subjects;
}
   

}
