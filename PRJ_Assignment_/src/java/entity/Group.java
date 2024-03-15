/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

public class Group implements IEntity{
private int gid;
private String gname;
private Subject subject;
private Teacher pic;
private int session_number;
private int attendance_percentage;

    public int getSession_number() {
        return session_number;
    }

    public void setSession_number(int session_number) {
        this.session_number = session_number;
    }

    public int getAttendance_percentage() {
        return attendance_percentage;
    }

    public void setAttendance_percentage(int attendance_percentage) {
        this.attendance_percentage = attendance_percentage;
    }


    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getPic() {
        return pic;
    }

    public void setPic(Teacher pic) {
        this.pic = pic;
    }

}
