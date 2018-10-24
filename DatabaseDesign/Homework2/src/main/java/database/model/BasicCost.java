package database.model;

public class BasicCost {
    private int id;
    private double callCost;
    private double smsCost;
    private double localDataCost;
    private double domesticDataCost;

    public BasicCost() {
    }

    public BasicCost(double callCost, double smsCost, double localDataCost, double domesticDataCost) {
        this.callCost = callCost;
        this.smsCost = smsCost;
        this.localDataCost = localDataCost;
        this.domesticDataCost = domesticDataCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCallCost() {
        return callCost;
    }

    public void setCallCost(double callCost) {
        this.callCost = callCost;
    }

    public double getSmsCost() {
        return smsCost;
    }

    public void setSmsCost(double smsCost) {
        this.smsCost = smsCost;
    }

    public double getLocalDataCost() {
        return localDataCost;
    }

    public void setLocalDataCost(double localDataCost) {
        this.localDataCost = localDataCost;
    }

    public double getDomesticDataCost() {
        return domesticDataCost;
    }

    public void setDomesticDataCost(double domesticDataCost) {
        this.domesticDataCost = domesticDataCost;
    }

    public double getDataCost(DataType type) {
        if (type == DataType.LOCAL) {
            return localDataCost;
        } else {
            return domesticDataCost;
        }
    }
}
