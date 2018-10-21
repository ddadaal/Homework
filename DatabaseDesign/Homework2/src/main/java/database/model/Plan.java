package database.model;

public class Plan {
    private int planId;
    private String name;
    private long cost;
    private int callMinutes;
    private long callExceedingCost;
    private int sms;
    private long smsExceedingCost;
    private double localData;
    private long localDataExceedingCost;
    private double domesticData;
    private long domesticDataExceedingCost;

    @Override
    public String toString() {
        return "Plan{" +
            "planId=" + planId +
            ", name='" + name + '\'' +
            ", cost=" + cost +
            ", callMinutes=" + callMinutes +
            ", callExceedingCost=" + callExceedingCost +
            ", sms=" + sms +
            ", smsExceedingCost=" + smsExceedingCost +
            ", localData=" + localData +
            ", localDataExceedingCost=" + localDataExceedingCost +
            ", domesticData=" + domesticData +
            ", domesticDataExceedingCost=" + domesticDataExceedingCost +
            '}';
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

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getCallMinutes() {
        return callMinutes;
    }

    public void setCallMinutes(int callMinutes) {
        this.callMinutes = callMinutes;
    }

    public long getCallExceedingCost() {
        return callExceedingCost;
    }

    public void setCallExceedingCost(long callExceedingCost) {
        this.callExceedingCost = callExceedingCost;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public long getSmsExceedingCost() {
        return smsExceedingCost;
    }

    public void setSmsExceedingCost(long smsExceedingCost) {
        this.smsExceedingCost = smsExceedingCost;
    }

    public double getLocalData() {
        return localData;
    }

    public void setLocalData(double localData) {
        this.localData = localData;
    }

    public long getLocalDataExceedingCost() {
        return localDataExceedingCost;
    }

    public void setLocalDataExceedingCost(long localDataExceedingCost) {
        this.localDataExceedingCost = localDataExceedingCost;
    }

    public double getDomesticData() {
        return domesticData;
    }

    public void setDomesticData(double domesticData) {
        this.domesticData = domesticData;
    }

    public long getDomesticDataExceedingCost() {
        return domesticDataExceedingCost;
    }

    public void setDomesticDataExceedingCost(long domesticDataExceedingCost) {
        this.domesticDataExceedingCost = domesticDataExceedingCost;
    }
}
