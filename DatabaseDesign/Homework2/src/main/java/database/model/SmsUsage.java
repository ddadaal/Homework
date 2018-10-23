package database.model;

import java.time.Instant;
import java.util.Date;

public class SmsUsage {
    private int userId;
    private Instant time;

    public SmsUsage(int userId, Instant time) {
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

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
