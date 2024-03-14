/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import entity.Grade;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradeDBContext extends DBContext<Grade> {
    StudentDBContext studentDBContext = new StudentDBContext();
    ExamDBContext examDBContext = new ExamDBContext();
    @Override
    public ArrayList<Grade> list() {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            String sql = "SELECT grid, eid, sid, score FROM Grade";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGrid(rs.getInt("grid"));
                grade.setExam(examDBContext.get(rs.getInt("eid")));
                grade.setStudent(studentDBContext.get(rs.getInt("sid")));
                grade.setScore(rs.getInt("score"));
                grades.add(grade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }

     @Override
    public void insert(Grade entity) {
        try {
            String sql = "INSERT INTO Grade (eid, sid, score) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, entity.getExam().getEid());
            stm.setInt(2, entity.getStudent().getSid());
            stm.setInt(3, entity.getScore());
            stm.executeUpdate();

            // Retrieve the generated key (grid)
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setGrid(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Grade entity) {
        try {
            String sql = "UPDATE Grade SET eid = ?, sid = ?, score = ? WHERE grid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getExam().getEid());
            stm.setInt(2, entity.getStudent().getSid());
            stm.setInt(3, entity.getScore());
            stm.setInt(4, entity.getGrid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Grade entity) {
        try {
            String sql = "DELETE FROM Grade WHERE grid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getGrid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Grade get(int id) {
        Grade grade = null;
        try {
            String sql = "SELECT grid, eid, sid, score FROM Grade WHERE grid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                grade = new Grade();
                grade.setGrid(rs.getInt("grid"));
                grade.setExam(examDBContext.get(rs.getInt("eid")));
                grade.setStudent(studentDBContext.get(rs.getInt("sid")));
                grade.setScore(rs.getInt("score"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grade;
    }
  public ArrayList<Grade> getGrade(int sid) {
    ArrayList<Grade> grades = new ArrayList<>();
    try {
        String sql = "SELECT grid, eid, sid, score FROM Grade WHERE sid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, sid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Grade grade = new Grade();
            grade.setGrid(rs.getInt("grid"));
            grade.setExam(examDBContext.get(rs.getInt("eid"))); // Assuming you have ExamDBContext
            grade.setStudent(studentDBContext.get(rs.getInt("sid"))); // Assuming you have StudentDBContext
            grade.setScore(rs.getInt("score"));
            grades.add(grade);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return grades;
}
}
