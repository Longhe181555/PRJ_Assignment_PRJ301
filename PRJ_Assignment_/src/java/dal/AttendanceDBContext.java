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
        String sql = "SELECT aid, ssid, sid, isPresent, Description FROM Attendance " +
                     "WHERE ssid = ? " +
                     "AND ssid IN (SELECT ssid FROM Session WHERE tid = ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, sessionId);
        stm.setInt(2, teacherId);
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
    public void updateAttendanceStatus(int aid, String isPresent) {
    try {
        String sql = "UPDATE Attendance SET isPresent = ? WHERE aid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, isPresent);
        stm.setInt(2, aid);
        stm.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
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
}
