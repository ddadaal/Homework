package database.model;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class UserPlan {
    private int transactionId;
    private String userId;
    private String planId;
    private LocalDateTime activateTime;
    private LocalDateTime orderTime;
    private LocalDateTime deactivateTime;

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

    public LocalDateTime getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(LocalDateTime activateTime) {
        this.activateTime = activateTime;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getDeactivateTime() {
        return deactivateTime;
    }

    public void setDeactivateTime(LocalDateTime deactivateTime) {
        this.deactivateTime = deactivateTime;
    }

    public boolean isActivated(LocalDateTime datetime) {
        return datetime.isAfter(activateTime) && (deactivateTime == null || deactivateTime.isAfter(datetime));
    }
}
