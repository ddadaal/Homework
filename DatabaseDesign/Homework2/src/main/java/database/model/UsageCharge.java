package database.model;

public class UsageCharge {
    private double extraAmount;
    private double charge;

    public UsageCharge(double extraAmount, double charge) {
        this.extraAmount = extraAmount;
        this.charge = charge;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(double extraAmount) {
        this.extraAmount = extraAmount;
    }
}
