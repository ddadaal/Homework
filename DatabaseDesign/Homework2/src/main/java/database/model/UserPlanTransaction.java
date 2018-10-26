package database.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class UserPlanTransaction {
    private String transactionId;
    private String userId;
    private String planId;
    private LocalDateTime time;
    private PlanAction action;


    @Override
    public String toString() {
        return "用户交易记录{" +
            "交易ID='" + transactionId + '\'' +
            ", 用户ID='" + userId + '\'' +
            ", 套餐ID='" + planId + '\'' +
            ", 交易时间=" + time +
            ", 交易操作=" + action +
            '}';
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public PlanAction getAction() {
        return action;
    }

    public void setAction(PlanAction action) {
        this.action = action;
    }
}
