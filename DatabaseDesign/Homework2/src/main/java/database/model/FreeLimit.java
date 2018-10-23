package database.model;

import java.time.Instant;
import java.util.Date;

public class FreeLimit {
    private int userId;
    private Instant queryTime;

    private int call;
    private int sms;
    private double localData;
    private double domesticData;

    public FreeLimit() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Instant getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Instant queryTime) {
        this.queryTime = queryTime;
    }

    public int getCall() {
        return call;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public double getLocalData() {
        return localData;
    }

    public void setLocalData(double localData) {
        this.localData = localData;
    }

    public double getDomesticData() {
        return domesticData;
    }

    public void setDomesticData(double domesticData) {
        this.domesticData = domesticData;
    }
}
