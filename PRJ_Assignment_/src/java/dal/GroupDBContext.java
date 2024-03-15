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

public class GroupDBContext extends DBContext<Group> {
SubjectDBContext sub = new SubjectDBContext();
TeacherDBContext t = new TeacherDBContext();
    public ArrayList<Group> list() {
    ArrayList<Group> grouptest = new ArrayList<>();
    try {
        String sql = "Select gid, gname, subid, pic FROM [Group]";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Group group = new Group();
            group.setGid(rs.getInt("gid"));
            group.setGname(rs.getString("gname"));
            group.setSubject(sub.get(rs.getInt("subid")));
            group.setPic(t.get(rs.getInt("pic")));
            
            

            grouptest.add(group);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return grouptest;
}

  
    @Override
    public void update(Group entity) {
        try {
            String sql = "UPDATE [Group] SET gname = ?, subid = ?, pic = ? WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getGname());
            stm.setInt(2, entity.getSubject().getSubid());
            stm.setInt(3, entity.getPic().getTid());
            stm.setInt(4, entity.getGid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Group entity) {
        try {
            String sql = "DELETE FROM [Group] WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getGid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
public Group get(int id) {
    Group group = null;
    try {
        String sql = "SELECT gid, gname, subid, pic, session_number, attendance_percentage FROM [Group] WHERE gid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            group = new Group();
            group.setGid(rs.getInt("gid"));
            group.setGname(rs.getString("gname"));
            group.setSubject(sub.get(rs.getInt("subid")));
            group.setPic(t.get(rs.getInt("pic")));
            group.setSession_number(rs.getInt("session_number"));
            group.setAttendance_percentage(rs.getInt("attendance_percentage"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return group;
}
    @Override
    public void insert(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  public ArrayList<Group> getBySid(int sid) {
    ArrayList<Group> groups = new ArrayList<>();
    try {
        String sql = "SELECT DISTINCT [Group].gid, [Group].gname, [Group].subid, [Group].pic " +
                     "FROM [Group] " +
                     "JOIN Enrollment ON [Group].gid = Enrollment.gid " +
                     "WHERE Enrollment.sid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, sid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Group group = new Group();
            group.setGid(rs.getInt("gid"));
            group.setGname(rs.getString("gname"));
            group.setSubject(sub.get(rs.getInt("subid")));
            group.setPic(t.get(rs.getInt("pic")));
            groups.add(group);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return groups;
}
  public ArrayList<Group> getByTid(int tid) {
    ArrayList<Group> groups = new ArrayList<>();
    try {
        String sql = "SELECT gid, gname, subid, pic FROM [Group] WHERE pic = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, tid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Group group = new Group();
            group.setGid(rs.getInt("gid"));
            group.setGname(rs.getString("gname"));
            group.setSubject(sub.get(rs.getInt("subid"))); // Assuming you have SubjectDBContext
            group.setPic(t.get(rs.getInt("pic"))); // Assuming you have TeacherDBContext
            groups.add(group);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return groups;
}

}
