package entity;
import java.sql.Date;
public class Attendance implements IEntity {

    private int aid;
    private Session session;
    private Student student;
    private String isPresent;
    private String description;
    private Date capturedTime;

    public Date getCapturedTime() {
        return capturedTime;
    }

    public void setCapturedTime(Date capturedTime) {
        this.capturedTime = capturedTime;
    }

    

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
