package database.model;

import java.util.Objects;

public class ServiceBill {
    private double total;
    private double extra;
    private double charge;
    private double limit;
    private double remaining;

    public ServiceBill(double total, double extra, double charge, double limit, double remaining) {
        this.total = total;
        this.extra = extra;
        this.charge = charge;
        this.limit = limit;
        this.remaining = remaining;
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


    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "服务账单{" +
            "总使用量=" + total +
            ", 套餐外使用量=" + extra +
            ", 套餐外使用量收费=" + charge +
            ", 套餐限额=" + limit +
            ", 套餐剩余=" + remaining +
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
            Double.compare(serviceBill.getLimit(), getLimit()) == 0 &&
            Double.compare(serviceBill.getRemaining(), getRemaining()) == 0
            ;
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
