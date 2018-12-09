package database.model;

public enum PlanAction {
    ORDER_IMMEDIATELY("立即订阅"),
    ORDER_NEXT_MONTH("下月订阅"),
    CANCEL_IMMEDIATELY("立即取消"),
    CANCEL_NEXT_MONTH("下月取消");

    String description;

    PlanAction(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
