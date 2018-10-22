package database.model;
import java.util.Date;

public class CallUsage {
    private String id;
    private String userId;
    private int quantity;
    private Date startTIme;
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
