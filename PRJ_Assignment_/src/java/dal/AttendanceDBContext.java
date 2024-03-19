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
            String sql = "Select aid, ssid, sid, isPresent, Description, Date,Description FROM Attendance";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent(rs.getString("isPresent"));
                attendance.setDescription(rs.getString("Description"));
                attendance.setCapturedTime(rs.getDate("Date"));
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

    public Attendance getBySsidandSid(int ssid, int sid) {
        Attendance attendance = null;
        try {
            String sql = "SELECT aid, ssid, sid, isPresent, Description, Date FROM Attendance WHERE ssid = ? and sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ssid);
            stm.setInt(2, sid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent(rs.getString("isPresent"));
                attendance.setDescription(rs.getString("Description"));
                attendance.setCapturedTime(rs.getDate("Date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendance;
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

    public ArrayList<Attendance> getBySidAndGid(int id, int gid) {
        ArrayList<Attendance> log = new ArrayList<Attendance>();
        try {
            String sql = "SELECT a.aid, se.ssid, e.sid, a.isPresent, a.Description, a.Date \n"
                    + "FROM Student s \n"
                    + "INNER JOIN Enrollment e ON s.sid = e.sid \n"
                    + "INNER JOIN [Group] g ON g.gid = e.gid \n"
                    + "INNER JOIN Session se ON se.gid = g.gid \n"
                    + "LEFT JOIN Attendance a ON a.sid = s.sid AND a.ssid = se.ssid \n"
                    + "WHERE s.sid = ? and g.gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAid(rs.getInt("aid"));
                attendance.setSession(ss.get(rs.getInt("ssid")));
                attendance.setStudent(s.get(rs.getInt("sid")));
                attendance.setIsPresent(rs.getString("isPresent"));
                attendance.setDescription(rs.getString("Description"));
                attendance.setCapturedTime(rs.getDate("Date"));
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

    public boolean checkAttendanceThreshold(int sid, int tid) {
    try {
        String sql = "SELECT COUNT(CASE WHEN isPresent = 'F' THEN 1 END) AS F_count,\n"
                   + "       g.session_number,\n"
                   + "       g.attendance_percentage\n"
                   + "FROM Attendance a\n"
                   + "JOIN Session s ON a.ssid = s.ssid\n"
                   + "JOIN [Group] g ON s.gid = g.gid\n"
                   + "WHERE a.sid = ? AND s.tid = ?\n"
                   + "GROUP BY g.session_number, g.attendance_percentage";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, sid);
        stm.setInt(2, tid);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            int F_count = rs.getInt("F_count");
            int session_number = rs.getInt("session_number");
            int attendance_percentage = rs.getInt("attendance_percentage");
            int threshold = (session_number * attendance_percentage) / 100;
            return F_count > threshold;
        }
    } catch (SQLException ex) {
        Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}


    @Override
    public void insert(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
