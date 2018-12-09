package database.model;

import java.time.LocalDateTime;

public class UserPlan {
    private Plan plan;
    private int transactionId;
    private LocalDateTime activateTime;
    private LocalDateTime orderTime;
    private LocalDateTime deactivateTime;

    @Override
    public String toString() {
        return "用户套餐订阅{" +
            "套餐=" + plan +
            ", 对应交易ID=" + transactionId +
            ", 生效时间=" + activateTime +
            ", 订阅时间=" + orderTime +
            ", 失效时间（NULL则为长期有效）=" + deactivateTime +
            '}';
    }

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
        return (dateTime.equals(activateTime) || dateTime.isAfter(activateTime)) && (deactivateTime == null || deactivateTime.isAfter(dateTime));
    }
}
