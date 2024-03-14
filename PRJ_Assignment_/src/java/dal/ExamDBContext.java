package dal;

import entity.Assessment;
import entity.Exam;
import entity.TimeSlot;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamDBContext extends DBContext<Exam> {

    AssessmentDBContext assessmentDBContext = new AssessmentDBContext();
    TimeSlotDBContext timeSlotDBContext = new TimeSlotDBContext();
    RoomDBContext roomDBContext = new RoomDBContext();
    @Override
    public ArrayList<Exam> list() {
        ArrayList<Exam> exams = new ArrayList<>();
        try {
            String sql = "SELECT eid, aid, date, slotTime, rid FROM Exam";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setEid(rs.getInt("eid"));
                exam.setAssessment(assessmentDBContext.get(rs.getInt("aid")));
                exam.setDate(rs.getDate("date"));
                exam.setTimeslot(timeSlotDBContext.get(rs.getInt("slotTime")));
                exam.setRoom(roomDBContext.get(rs.getInt("rid")));
                exams.add(exam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exams;
    }

    @Override
    public void insert(Exam entity) {
        try {
            String sql = "INSERT INTO Exam (aid, date, tsid) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getAssessment().getAid());
            stm.setDate(2, entity.getDate());
            stm.setInt(3, entity.getTimeslot().getTsid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Exam entity) {
        // Implement update method if needed
    }

    @Override
    public void delete(Exam entity) {
        try {
            String sql = "DELETE FROM Exam WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getEid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Exam get(int id) {
        Exam exam = null;
        try {
            String sql = "SELECT eid, aid, date, slotTime FROM Exam WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                exam = new Exam();
                exam.setEid(rs.getInt("eid"));
                exam.setAssessment(assessmentDBContext.get(rs.getInt("aid")));
                exam.setDate(rs.getDate("date"));
                exam.setTimeslot(timeSlotDBContext.get(rs.getInt("tsid")));
                exam.setRoom(roomDBContext.get(rs.getInt("rid")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exam;
    }
    
    public ArrayList<Exam> getBySid(int sid) {
    ArrayList<Exam> exams = new ArrayList<>();
    try {
        String sql = "SELECT e.eid, e.aid, e.Date, e.slotTime, e.rid, e.On_Class " +
                     "FROM Exam e " +
                     "JOIN Grade g ON e.eid = g.eid " +
                     "WHERE g.sid = ? AND e.On_Class <> 1";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, sid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Exam exam = new Exam();
            exam.setEid(rs.getInt("eid"));
            exam.setAssessment(assessmentDBContext.get(rs.getInt("aid"))); // Assuming you have AssessmentDBContext
            exam.setDate(rs.getDate("Date"));
            exam.setTimeslot(timeSlotDBContext.get(rs.getInt("slotTime"))); // Assuming you have TimeSlotDBContext
            exam.setRoom(roomDBContext.get(rs.getInt("rid")));
            exams.add(exam);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return exams;
}
}
