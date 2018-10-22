package database.model;

public class Plan {
    private int planId;
    private String name;
    private double cost;
    private int callMinutes;
    private double callExceedingCost;
    private int sms;
    private double smsExceedingCost;
    private double localData;
    private double localDataExceedingCost;
    private double domesticData;
    private double domesticDataExceedingCost;

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

    public double getCallExceedingCost() {
        return callExceedingCost;
    }

    public void setCallExceedingCost(double callExceedingCost) {
        this.callExceedingCost = callExceedingCost;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public double getSmsExceedingCost() {
        return smsExceedingCost;
    }

    public void setSmsExceedingCost(double smsExceedingCost) {
        this.smsExceedingCost = smsExceedingCost;
    }

    public double getLocalData() {
        return localData;
    }

    public void setLocalData(double localData) {
        this.localData = localData;
    }

    public double getLocalDataExceedingCost() {
        return localDataExceedingCost;
    }

    public void setLocalDataExceedingCost(double localDataExceedingCost) {
        this.localDataExceedingCost = localDataExceedingCost;
    }

    public double getDomesticData() {
        return domesticData;
    }

    public void setDomesticData(double domesticData) {
        this.domesticData = domesticData;
    }

    public double getDomesticDataExceedingCost() {
        return domesticDataExceedingCost;
    }

    public void setDomesticDataExceedingCost(double domesticDataExceedingCost) {
        this.domesticDataExceedingCost = domesticDataExceedingCost;
    }
}
