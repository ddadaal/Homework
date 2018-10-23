package database.model;

import java.util.Date;

public class SmsUsage {
    private int userId;
    private Date time;

    public SmsUsage(int userId, Date time) {
        this.userId = userId;
        this.time = time;
    }

    public SmsUsage() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
