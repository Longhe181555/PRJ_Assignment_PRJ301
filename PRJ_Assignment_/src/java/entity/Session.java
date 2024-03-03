/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

import java.sql.Date;
import java.util.ArrayList;


public class Session implements IEntity {
    private int ssid;
    private Group group;
    private Teacher teacher;
    private Room room;
    private TimeSlot timeslot;
    private ArrayList<Attendance> atts;

    public ArrayList<Attendance> getAtts() {
        return atts;
    }

    public void setAtts(ArrayList<Attendance> atts) {
        this.atts = atts;
    }

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }
    private Date date;
    private String isTaken;

   
}
  

