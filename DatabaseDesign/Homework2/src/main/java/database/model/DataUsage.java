package database.model;
import java.time.Instant;
import java.util.Date;

public class DataUsage {
    private int userId;
    private DataUsageType dataType;
    private double amount
        ;
    private Instant startTime;
    private Instant endTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DataUsageType getDataType() {
        return dataType;
    }

    public void setDataType(DataUsageType dataType) {
        this.dataType = dataType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
