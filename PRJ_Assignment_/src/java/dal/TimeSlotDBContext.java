/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.TimeSlot;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeSlotDBContext extends DBContext<TimeSlot> {

    @Override
    public ArrayList<entity.TimeSlot> list() {
        ArrayList<entity.TimeSlot> timeSlots = new ArrayList<>();
        try {
            String sql = "SELECT tsid, [START HOUR], [START MINUTE], [END HOUR], [END MINUTE] FROM TimeSlot";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                entity.TimeSlot ts = new entity.TimeSlot();
                ts.setTsid(rs.getInt("tsid"));
                ts.setStartHour(rs.getInt("START HOUR"));
                ts.setStartMinute(rs.getInt("START MINUTE"));
                ts.setEndHour(rs.getInt("END HOUR"));
                ts.setEndMinute(rs.getInt("END MINUTE"));
                timeSlots.add(ts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timeSlots;
    }

    @Override
    public void insert(entity.TimeSlot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(entity.TimeSlot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(entity.TimeSlot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public entity.TimeSlot get(int id) {
        entity.TimeSlot timeSlot = null;
        try {
            String sql = "SELECT tsid, [START HOUR], [START MINUTE], [END HOUR], [END MINUTE] FROM TimeSlot WHERE tsid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                timeSlot = new entity.TimeSlot();
                timeSlot.setTsid(rs.getInt("tsid"));
                timeSlot.setStartHour(rs.getInt("START HOUR"));
                timeSlot.setStartMinute(rs.getInt("START MINUTE"));
                timeSlot.setEndHour(rs.getInt("END HOUR"));
                timeSlot.setEndMinute(rs.getInt("END MINUTE"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timeSlot;
    }

}
