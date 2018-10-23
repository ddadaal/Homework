package database.model;

public class UsageCharge {
    private double fee;

    public UsageCharge(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
