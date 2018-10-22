package database.model;
import java.util.Date;

public class DataUsage {
    private String userId;
    private DataUsageType dataType;
    private double quantity;
    private Date startTime;
    private Date endTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DataUsageType getDataType() {
        return dataType;
    }

    public void setDataType(DataUsageType dataType) {
        this.dataType = dataType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
