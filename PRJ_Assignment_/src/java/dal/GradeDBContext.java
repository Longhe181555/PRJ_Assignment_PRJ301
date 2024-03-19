/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Exam;
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

    public ArrayList<Grade> getBySubidAndSid(int subid, int sid) {
        ArrayList<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.[grid]\n"
                + "      ,g.[eid]\n"
                + "      ,g.[sid]\n"
                + "      ,g.[score] FROM Grade g\n"
                + "join Exam e on g.eid = e.eid\n"
                + "join Assessment a on e.aid = a.aid\n"
                + "where a.subid = ? and g.sid = ?\n"
                + "Order by a.name ";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, subid);
            stm.setInt(2, sid);
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
            ex.printStackTrace();
        }

        return grades;
    }

    public ArrayList<Grade> getByGidAndAid(int gid, int aid) {
        ArrayList<Grade> grades = new ArrayList<>();
        String sql = "SELECT  s.[sid]\n"
                + "      ,[sname]\n"
                + "	  ,gr.grid\n"
                + "	  ,gr.score\n"
                + "	  ,a.name\n"
                + "	  ,a.weight\n"
                + "	  ,e.eid\n"
                + "  FROM [Student] s\n"
                + "  join Enrollment er on er.sid = s.sid\n"
                + "  join [Group] g on g.gid = er.gid \n"
                + "  left join Grade gr on gr.sid = s.sid\n"
                + "  inner join Assessment a on a.subid = g.subid\n"
                + "   join Exam e on e.aid = a.aid     \n"
                + "  where er.gid = ? and a.aid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, gid);
            stm.setInt(2, aid);
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
            ex.printStackTrace();
        }

        return grades;
    }
    public void takeGrades(int eid, int gid, ArrayList<Grade> grades) {
    try {
        connection.setAutoCommit(false);

        // Delete existing grades for the given exam
        String sqlRemoveGrades = "DELETE FROM Grade WHERE eid = ? and sid in (\n" +
"	  select s.sid from Student s\n" +
"	  join Enrollment e on s.sid = e.sid\n" +
"	  Where e.gid = ?\n" +
"	  )";
        try (PreparedStatement stmRemoveGrades = connection.prepareStatement(sqlRemoveGrades)) {
            stmRemoveGrades.setInt(1, eid);
            stmRemoveGrades.setInt(2, gid);
            stmRemoveGrades.executeUpdate();
        }

        // Insert new grades
        String sqlInsertGrade = "INSERT INTO Grade (grid ,eid, sid, score) VALUES (?,?, ?, ?)";
        try (PreparedStatement stmInsertGrade = connection.prepareStatement(sqlInsertGrade)) {
            for (Grade grade : grades) {
                stmInsertGrade.setInt(1, eid*grade.getStudent().getSid());
                stmInsertGrade.setInt(2, eid);
                stmInsertGrade.setInt(3, grade.getStudent().getSid());
                stmInsertGrade.setInt(4, grade.getScore());
                stmInsertGrade.addBatch();
            }
            stmInsertGrade.executeBatch();
        }

        connection.commit();
    } catch (SQLException ex) {
        Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        try {
            connection.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
        }
    } finally {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}
