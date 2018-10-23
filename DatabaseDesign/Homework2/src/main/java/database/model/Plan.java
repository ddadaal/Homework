package database.model;

public class Plan {
    private int planId;
    private String name;
    private double cost;
    private int callMinutes;
    private int sms;
    private double localData;
    private double domesticData;

    public Plan(String name, double cost, int callMinutes, int sms, double localData, double domesticData) {
        this.name = name;
        this.cost = cost;
        this.callMinutes = callMinutes;
        this.sms = sms;
        this.localData = localData;
        this.domesticData = domesticData;
    }

    public Plan() {
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCallMinutes() {
        return callMinutes;
    }

    public void setCallMinutes(int callMinutes) {
        this.callMinutes = callMinutes;
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
