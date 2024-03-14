/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Teacher;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherDBContext extends DBContext<Teacher> {

    @Override
    public ArrayList<Teacher> list() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            String sql = "SELECT tid, tname, tgmail FROM Teacher";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTid(rs.getInt("tid"));
                teacher.setTname(rs.getString("tname"));
                teacher.setTgmail(rs.getString("tgmail"));
                teachers.add(teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teachers;
    }

    @Override
    public void insert(Teacher teacher) {
        try {
            String sql = "INSERT INTO Teacher (tname, tgmail) VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, teacher.getTname());
            stm.setString(2, teacher.getTgmail());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Teacher teacher) {
        try {
            String sql = "UPDATE Teacher SET tname = ?, tgmail = ? WHERE tid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, teacher.getTname());
            stm.setString(2, teacher.getTgmail());
            stm.setInt(3, teacher.getTid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Teacher teacher) {
        try {
            String sql = "DELETE FROM Teacher WHERE tid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, teacher.getTid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Teacher get(int id) {
        try {
            String sql = "SELECT tid, tname, tgmail FROM Teacher WHERE tid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTid(rs.getInt("tid"));
                teacher.setTname(rs.getString("tname"));
                teacher.setTgmail(rs.getString("tgmail"));
                return teacher;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   public ArrayList<Teacher> search(int tid, String filterName, String filterEmail) {
    ArrayList<Teacher> teachers = new ArrayList<>();
    try {
        int parameterIndex = 1;
        boolean whereInside = false;
        String sql = "SELECT t.tid, t.tname, t.tgmail\n"
                + "FROM Teacher t\n";

        if (tid > 0) {
            sql += " WHERE t.tid = ?";
            whereInside = true;
        }
        if (filterName != null && !filterName.isEmpty()) {
            if (whereInside) {
                sql += " AND t.tname LIKE ?";
            } else {
                sql += " WHERE t.tname LIKE ?";
                whereInside = true;
            }
        }
        if (filterEmail != null && !filterEmail.isEmpty()) {
            if (whereInside) {
                sql += " AND t.tgmail LIKE ?";
            } else {
                sql += " WHERE t.tgmail LIKE ?";
                whereInside = true;
            }
        }

        PreparedStatement stm = connection.prepareStatement(sql);

        if (tid > 0) {
            stm.setInt(parameterIndex++, tid);
        }
        if (filterName != null && !filterName.isEmpty()) {
            stm.setString(parameterIndex++, "%" + filterName + "%");
        }
        if (filterEmail != null && !filterEmail.isEmpty()) {
            stm.setString(parameterIndex++, "%" + filterEmail + "%");
        }

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Teacher t = new Teacher();
            t.setTid(rs.getInt("tid"));
            t.setTname(rs.getString("tname"));
            t.setTgmail(rs.getString("tgmail"));
            teachers.add(t);
        }
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return teachers;
}
}
