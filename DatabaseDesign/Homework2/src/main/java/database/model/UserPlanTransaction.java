package database.model;

import java.time.Instant;
import java.util.Date;

public class UserPlanTransaction {
    private String transactionId;
    private String userId;
    private String planId;
    private String userPlanId;
    private Instant time;
    private PlanAction action;

    public String getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(String userPlanId) {
        this.userPlanId = userPlanId;
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

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public PlanAction getAction() {
        return action;
    }

    public void setAction(PlanAction action) {
        this.action = action;
    }
}
