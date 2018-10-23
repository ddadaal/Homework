package database.model;
import java.util.Date;

public class CallUsage {
    private String userId;
    private int duration;
    private Date startTIme;
    private Date endTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartTIme() {
        return startTIme;
    }

    public void setStartTIme(Date startTIme) {
        this.startTIme = startTIme;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
