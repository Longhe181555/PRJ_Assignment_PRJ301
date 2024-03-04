package dal;

import entity.Attendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceDBContext extends DBContext<Attendance> {

    StudentDBContext s = new StudentDBContext();
    SessionDBContext ss = new SessionDBContext();

    @Override
    public ArrayList<Attendance> list() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try {
            String sql = "SELECT aid, ssid, sid, isPresent, Description FROM Attendance";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent(rs.getString("isPresent"));
                attendance.setDescription(rs.getString("Description"));
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

    public void insert(Attendance entity) {
        try {
            String sql = "INSERT INTO Attendance (ssid, sid, isPresent, Description) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSession().getSsid());
            stm.setInt(2, entity.getStudent().getSid());
            stm.setString(3, entity.getIsPresent());
            stm.setString(4, entity.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Attendance entity) {
        try {
            String sql = "UPDATE Attendance SET ssid = ?, sid = ?, isPresent = ?, Description = ? WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSession().getSsid());
            stm.setInt(2, entity.getStudent().getSid());
            stm.setString(3, entity.getIsPresent());
            stm.setString(4, entity.getDescription());
            stm.setInt(5, entity.getAid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendance entity) {
        try {
            String sql = "DELETE FROM Attendance WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getAid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Attendance get(int id) {
        Attendance attendance = null;
        try {
            String sql = "SELECT aid, ssid, sid, isPresent, Description FROM Attendance WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent(rs.getString("isPresent"));
                attendance.setDescription(rs.getString("Description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendance;
    }

    public ArrayList<Attendance> getBySid(int id) {
        ArrayList<Attendance> log = new ArrayList<Attendance>();
        try {
            String sql = "SELECT aid, ssid, sid, isPresent, Description FROM Attendance WHERE sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent(rs.getString("isPresent"));
                attendance.setDescription(rs.getString("Description"));
                log.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return log;
    }

    public ArrayList<Attendance> getAttendanceByTeacherAndSession(int teacherId, int sessionId) {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try {
            String sql = "SELECT a.aid, se.ssid, e.sid, a.isPresent, a.Description "
                    + "FROM Student s "
                    + "INNER JOIN Enrollment e ON s.sid = e.sid "
                    + "INNER JOIN [Group] g ON g.gid = e.gid "
                    + "INNER JOIN Session se ON se.gid = g.gid "
                    + "LEFT JOIN Attendance a ON a.sid = s.sid AND a.ssid = se.ssid "
                    + "WHERE se.ssid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent("F");
                attendance.setDescription(rs.getString("Description"));
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

    public ArrayList<Attendance> getAttendanceBySession(int sessionId) {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT a.aid, se.ssid, e.sid, a.isPresent, a.Description "
                    + "FROM Student s "
                    + "INNER JOIN Enrollment e ON s.sid = e.sid "
                    + "INNER JOIN [Group] g ON g.gid = e.gid "
                    + "INNER JOIN Session se ON se.gid = g.gid "
                    + "LEFT JOIN Attendance a ON a.sid = s.sid AND a.ssid = se.ssid "
                    + "WHERE se.ssid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent((rs.getString("isPresent") != null) ? rs.getString("isPresent") : "F");
                attendance.setDescription(rs.getString("Description"));
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

   

    public void updateAttendances(int aid, String isPresent, String description) {
        try {
            String sql = "UPDATE Attendance SET isPresent = ?, Description = ? WHERE aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, isPresent);
            stm.setString(2, description);
            stm.setInt(3, aid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void takeAttendances(int ssid, ArrayList<Attendance> atts) {
        try {
            connection.setAutoCommit(false);

            // Delete existing attendances for the given session
            String sqlRemoveAtts = "DELETE FROM Attendance WHERE ssid = ?";
            try (PreparedStatement stmRemoveAtts = connection.prepareStatement(sqlRemoveAtts)) {
                stmRemoveAtts.setInt(1, ssid);
                stmRemoveAtts.executeUpdate();
            }

            // Insert new attendances
            String sqlInsertAtt = "INSERT INTO Attendance (ssid, sid, description, isPresent, Date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmInsertAtt = connection.prepareStatement(sqlInsertAtt)) {
                for (Attendance att : atts) {
                    stmInsertAtt.setInt(1, ssid);
                    stmInsertAtt.setInt(2, att.getStudent().getSid());
                    stmInsertAtt.setString(3, att.getDescription());
                    stmInsertAtt.setString(4, att.getIsPresent());  // Use "T" or "F" for isPresent
                    stmInsertAtt.setDate(5, new java.sql.Date(System.currentTimeMillis()));  // Use current date
                    stmInsertAtt.addBatch();
                }
                stmInsertAtt.executeBatch();
            }

            // Update the isAttended flag in Lession
            String sqlUpdateLession = "UPDATE Session SET isTaken = 'T' WHERE ssid = ?";
            try (PreparedStatement stmUpdateLession = connection.prepareStatement(sqlUpdateLession)) {
                stmUpdateLession.setInt(1, ssid);
                stmUpdateLession.executeUpdate();
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
