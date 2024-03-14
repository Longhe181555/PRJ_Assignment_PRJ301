/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Room;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomDBContext extends DBContext<Room> {

     @Override
    public ArrayList<Room> list() {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            String sql = "SELECT rid, rnumber FROM Room";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRid(rs.getInt("rid"));
                room.setRnumber(rs.getString("rnumber"));
                rooms.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

    @Override
    public void insert(Room entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Room entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Room entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public Room get(int id) {
    Room room = null;
    try {
        String sql = "SELECT rid, rnumber FROM Room WHERE rid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            room = new Room();
            room.setRid(rs.getInt("rid"));
            room.setRnumber(rs.getString("rnumber"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return room;
}


   

}
