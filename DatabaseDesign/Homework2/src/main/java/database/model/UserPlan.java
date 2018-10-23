package database.model;
import java.time.Instant;
import java.util.Date;

public class UserPlan {
    private int transactionId;
    private String userId;
    private String planId;
    private Instant activateTime;
    private Instant orderTime;
    private Instant deactivateTime;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Instant getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Instant activateTime) {
        this.activateTime = activateTime;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
    }

    public Instant getDeactivateTime() {
        return deactivateTime;
    }

    public void setDeactivateTime(Instant deactivateTime) {
        this.deactivateTime = deactivateTime;
    }
}
