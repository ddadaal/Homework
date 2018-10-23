package database.model;

import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Date;

public class CallUsage {
    private int userId;
    private int duration;
    private Instant startTime;
    private Instant endTime;

    public CallUsage() {
    }

    public CallUsage(int userId, Instant startTime, Instant endTime) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;


        double diffInSeconds = endTime.getEpochSecond() - startTime.getEpochSecond();
        if (diffInSeconds < 0) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
        this.duration = (int) (diffInSeconds /60) + 1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
}
