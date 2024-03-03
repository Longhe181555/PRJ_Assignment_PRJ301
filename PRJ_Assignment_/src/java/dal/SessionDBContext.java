/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionDBContext extends DBContext<Session> {

    GroupDBContext g = new GroupDBContext();
    TeacherDBContext t = new TeacherDBContext();
    RoomDBContext r = new RoomDBContext();
    TimeSlotDBContext ts = new TimeSlotDBContext();

    @Override
    public ArrayList<Session> list() {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT ssid, gid, tid, rid, tsid, Date, isTaken FROM Session";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setSsid(rs.getInt("ssid"));
                session.setGroup(g.get(rs.getInt("gid")));
                session.setTeacher(t.get(rs.getInt("tid")));
                session.setRoom(r.get(rs.getInt("rid")));
                session.setTimeslot(ts.get(rs.getInt("tsid")));
                session.setDate(rs.getDate("Date"));
                session.setIsTaken(rs.getString("isTaken"));
                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        Session session = null;
        try {
            String sql = "SELECT ssid, gid, tid, rid, tsid, Date, isTaken FROM Session WHERE ssid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                session = new Session();
                session.setSsid(rs.getInt("ssid"));
                session.setGroup(g.get(rs.getInt("gid")));
                session.setTeacher(t.get(rs.getInt("tid")));
                session.setRoom(r.get(rs.getInt("rid")));
                session.setTimeslot(ts.get(rs.getInt("tsid")));
                session.setDate(rs.getDate("Date"));
                session.setIsTaken(rs.getString("isTaken"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return session;
    }
    public ArrayList<Session> listByTeacherId(int teacherId) {
    ArrayList<Session> sessions = new ArrayList<>();
    try {
        String sql = "SELECT ssid, gid, tid, rid, tsid, Date, isTaken FROM Session WHERE tid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, teacherId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Session session = new Session();
            session.setSsid(rs.getInt("ssid"));
            session.setGroup(g.get(rs.getInt("gid")));
            session.setTeacher(t.get(rs.getInt("tid")));
            session.setRoom(r.get(rs.getInt("rid")));
            session.setTimeslot(ts.get(rs.getInt("tsid")));
            session.setDate(rs.getDate("Date"));
            session.setIsTaken(rs.getString("isTaken"));
            sessions.add(session);
        }
    } catch (SQLException ex) {
        Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sessions;
}
   public void updateIsTakenStatus(int sessionId, String isTaken) {
        try {
            String sql = "UPDATE Session SET isTaken = ? WHERE ssid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, isTaken);
            stm.setInt(2, sessionId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public ArrayList<Session> getBy(int tid, Date from, Date to) {
        ArrayList<Session> sess = new ArrayList<>();
        try {
            String sql = "SELECT ses.ssid, ses.gid, ses.tid, ses.rid, ses.tsid, ses.Date, ses.isTaken FROM Session ses " +
                         "WHERE ses.tid = ? AND ses.[date] >= ? AND ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setSsid(rs.getInt("ssid"));
                session.setGroup(g.get(rs.getInt("gid")));
                session.setTeacher(t.get(rs.getInt("tid")));
                session.setRoom(r.get(rs.getInt("rid")));
                session.setTimeslot(ts.get(rs.getInt("tsid")));
                session.setDate(rs.getDate("Date"));
                session.setIsTaken(rs.getString("isTaken"));
                sess.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sess;
    }
}
