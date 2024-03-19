/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Assessment;
import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssessmentDBContext extends DBContext<Assessment> {
    SubjectDBContext sub = new SubjectDBContext();

    public ArrayList<Assessment> list() {
        ArrayList<Assessment> assessments = new ArrayList<>();
        try {
            String sql = "SELECT aid, subid, weight, name FROM [Assessment]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assessment assessment = new Assessment();
                assessment.setAid(rs.getInt("aid"));
                assessment.setWeight(rs.getInt("weight"));
                assessment.setSubject(sub.get(rs.getInt("subid")));
                assessment.setAname(rs.getString("name"));

                assessments.add(assessment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assessments;
    }
    public ArrayList<Assessment> listBySubid(int subid) {
        ArrayList<Assessment> assessments = new ArrayList<>();
        try {
            String sql = "SELECT aid, subid, weight, name,on_Class FROM [Assessment] WHERE subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assessment assessment = new Assessment();
                assessment.setAid(rs.getInt("aid"));
                assessment.setWeight(rs.getInt("weight"));
                assessment.setSubject(sub.get(rs.getInt("subid")));
                assessment.setAname(rs.getString("name"));
                assessment.setOn_Class(rs.getBoolean("on_Class"));
                assessments.add(assessment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assessments;
    }

    @Override
    public void update(Assessment entity) {
        try {
            String sql = "UPDATE [Assessment] SET subid = ?, weight = ?, name = ? WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSubject().getSubid());
            stm.setInt(2, entity.getWeight());
            stm.setString(3, entity.getAname());
            stm.setInt(4, entity.getAid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Assessment entity) {
        try {
            String sql = "DELETE FROM [Assessment] WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getAid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Assessment get(int id) {
        Assessment assessment = new Assessment();
        try {
            String sql = "SELECT aid, subid, weight, name FROM [Assessment] WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                assessment.setAid(rs.getInt("aid"));
                assessment.setWeight(rs.getInt("weight"));
                assessment.setSubject(sub.get(rs.getInt("subid")));
                assessment.setAname(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assessment;
    }

    @Override
    public void insert(Assessment entity) {
        try {
            String sql = "INSERT INTO [Assessment] (subid, weight, name) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSubject().getSubid());
            stm.setInt(2, entity.getWeight());
            stm.setString(3, entity.getAname());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
