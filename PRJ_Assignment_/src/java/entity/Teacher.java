/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;


public class Teacher implements IEntity{
    private int tid;
    private String tname;
    private String tgmail;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTgmail() {
        return tgmail;
    }

    public void setTgmail(String tgmail) {
        this.tgmail = tgmail;
    }
    
}
