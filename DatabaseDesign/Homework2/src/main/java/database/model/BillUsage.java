package database.model;

import java.util.Objects;

public class BillUsage {
    private double total;
    private double extra;
    private double charge;
    private double limit;

    public BillUsage(double total, double extra, double charge, double limit) {
        this.total = total;
        this.extra = extra;
        this.charge = charge;
        this.limit = limit;
    }

    public BillUsage() {
    }

    public static BillUsage fromUsage(Usage usage, double chargePerUnit) {
        BillUsage billUsage = new BillUsage();
        billUsage.total = usage.getTotal();
        billUsage.extra = usage.getTotal() > usage.getLimit() ? usage.getTotal() - usage.getLimit() : 0;
        billUsage.charge = billUsage.extra * chargePerUnit;
        billUsage.limit = usage.getLimit();
        return billUsage;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "BillUsage{" +
            "total=" + total +
            ", extra=" + extra +
            ", charge=" + charge +
            ", limit=" + limit +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillUsage)) return false;
        BillUsage billUsage = (BillUsage) o;
        return Double.compare(billUsage.getTotal(), getTotal()) == 0 &&
            Double.compare(billUsage.getExtra(), getExtra()) == 0 &&
            Double.compare(billUsage.getCharge(), getCharge()) == 0;
    }



    @Override
    public int hashCode() {

        return Objects.hash(getTotal(), getExtra(), getCharge());
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
}
