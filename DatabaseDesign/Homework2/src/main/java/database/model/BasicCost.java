package database.model;

import java.util.Date;

public class BasicCost {
    private int id;
    private long callCost;
    private long smsCost;
    private long localDataCost;
    private long domesticDataCost;
    private Date activateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCallCost() {
        return callCost;
    }

    public void setCallCost(long callCost) {
        this.callCost = callCost;
    }

    public long getSmsCost() {
        return smsCost;
    }

    public void setSmsCost(long smsCost) {
        this.smsCost = smsCost;
    }

    public long getLocalDataCost() {
        return localDataCost;
    }

    public void setLocalDataCost(long localDataCost) {
        this.localDataCost = localDataCost;
    }

    public long getDomesticDataCost() {
        return domesticDataCost;
    }

    public void setDomesticDataCost(long domesticDataCost) {
        this.domesticDataCost = domesticDataCost;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }
}
