package database.model;

import java.time.LocalDateTime;

public class ActivePlan {
    private Plan plan;
    private int transactionId;
    private LocalDateTime activateTime;
    private LocalDateTime orderTime;
    private LocalDateTime deactivateTime;


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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

    public boolean isActivatedAt(LocalDateTime dateTime) {
        return dateTime.equals(activateTime) || dateTime.isAfter(activateTime);
    }
}
