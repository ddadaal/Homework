package database.model;

public class Usage {
    private double limit;
    private double total;

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * 获得可用量。若无，返回0
     * @return 剩余可用量
     */
    public double getRemaining() {
        return Math.max(limit - total, 0);
    }
}
