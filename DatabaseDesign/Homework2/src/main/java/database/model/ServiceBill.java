package database.model;

import java.util.Objects;

public class ServiceBill {
    private double total;
    private double extra;
    private double charge;
    private double limit;

    public ServiceBill(double total, double extra, double charge, double limit) {
        this.total = total;
        this.extra = extra;
        this.charge = charge;
        this.limit = limit;
    }

    public ServiceBill() {
    }

//    public static ServiceBill fromUsage(Usage usage, double chargePerUnit) {
//        ServiceBill billUsage = new ServiceBill();
//        billUsage.total = usage.getTotal();
//        billUsage.extra = usage.getTotal() > usage.getLimit() ? usage.getTotal() - usage.getLimit() : 0;
//        billUsage.charge = billUsage.extra * chargePerUnit;
//        billUsage.limit = usage.getLimit();
//        return billUsage;
//    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ServiceBill{" +
            "total=" + total +
            ", extra=" + extra +
            ", charge=" + charge +
            ", limit=" + limit +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceBill)) return false;
        ServiceBill serviceBill = (ServiceBill) o;
        return Double.compare(serviceBill.getTotal(), getTotal()) == 0 &&
            Double.compare(serviceBill.getExtra(), getExtra()) == 0 &&
            Double.compare(serviceBill.getCharge(), getCharge()) == 0 &&
            Double.compare(serviceBill.getLimit(), getLimit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotal(), getExtra(), getCharge(), getLimit());
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
