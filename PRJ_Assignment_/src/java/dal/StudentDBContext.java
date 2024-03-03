/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Student;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDBContext extends DBContext<Student> {

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT sid, sname, sgender FROM Student"; // Remove "dob" from the query
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setSid(rs.getInt("sid"));
                student.setSname(rs.getString("sname"));
                student.setSgender(rs.getBoolean("sgender"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Override
    public Student get(int id) {
        try {
            String sql = "SELECT sid, sname, sgender FROM Student WHERE sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setSgender(rs.getBoolean("sgender"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Student entity) {
        try {
            String sql = "INSERT INTO Student(sname, sgender) VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getSname());
            stm.setBoolean(2, entity.isSgender());
            stm.executeUpdate();

            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                entity.setSid(generatedId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Student entity) {
        try {
            String sql = "UPDATE Student SET sname = ?, sgender = ? WHERE sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getSname());
            stm.setBoolean(2, entity.isSgender());
            stm.setInt(3, entity.getSid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Student entity) {
        try {
            String sql = "DELETE FROM Student WHERE sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int sid) {
        try {
            String sql = "DELETE FROM Student WHERE sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Student> getByGid(int groupId) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Student.sid, Student.sname, Student.sgender "
                    + "FROM Student "
                    + "JOIN Attendance ON Student.sid = Attendance.sid "
                    + "JOIN Session ON Attendance.ssid = Session.ssid "
                    + "JOIN [Group] ON Session.gid = [Group].gid "
                    + "WHERE [Group].gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setSid(rs.getInt("sid"));
                student.setSname(rs.getString("sname"));
                student.setSgender(rs.getBoolean("sgender"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public ArrayList<Student> search(int sid, String filterName, int filterGender) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            int parameterIndex = 1;
            boolean whereInside = false;
            String sql = "SELECT s.sid, s.sname, s.sgender\n"
                    + "FROM Student s\n";

            if (sid > 0) {
                sql += " WHERE s.sid = ?";
                whereInside = true;
            }
            if (filterName != null && !filterName.isEmpty()) {
                if (whereInside) {
                    sql += " AND s.sname LIKE ?";
                } else {
                    sql += " WHERE s.sname LIKE ?";
                    whereInside = true;
                }
            }
            if (filterGender != 2) {
                if (whereInside) {
                    sql += " AND s.sgender = ?";
                } else {
                    sql += " WHERE s.sgender = ?";
                    whereInside = true;
                }
            }

            PreparedStatement stm = connection.prepareStatement(sql);

            if (sid > 0) {
                stm.setInt(parameterIndex++, sid);
            }
            if (filterName != null && !filterName.isEmpty()) {
                stm.setString(parameterIndex++, "%" + filterName + "%");
            }
            if (filterGender != 2) {
                stm.setInt(parameterIndex++, filterGender);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setSgender(rs.getBoolean("sgender"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
}
