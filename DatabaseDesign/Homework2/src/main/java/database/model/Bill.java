package database.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {
    // basic information
    private int userId;
    private String name;

    // bill date range
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // applied basic cost
    private BasicCost basicCost;

    // active plans
    private List<Plan> activePlanList;

    // usages
    private BillUsage localDataUsage;
    private BillUsage domesticDataUsage;
    private BillUsage callUsage;
    private BillUsage smsUsage;

    // total fees
    public double getTotalCharge() {
        return localDataUsage.getCharge()
            + domesticDataUsage.getCharge()
            + callUsage.getCharge()
            + smsUsage.getCharge()
            + activePlanList.stream().mapToDouble(Plan::getCost).sum();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public BasicCost getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(BasicCost basicCost) {
        this.basicCost = basicCost;
    }

    public List<Plan> getActivePlanList() {
        return activePlanList;
    }

    public void setActivePlanList(List<Plan> activePlanList) {
        this.activePlanList = activePlanList;
    }

    public BillUsage getLocalDataUsage() {
        return localDataUsage;
    }

    public void setLocalDataUsage(BillUsage localDataUsage) {
        this.localDataUsage = localDataUsage;
    }

    public BillUsage getDomesticDataUsage() {
        return domesticDataUsage;
    }

    public void setDomesticDataUsage(BillUsage domesticDataUsage) {
        this.domesticDataUsage = domesticDataUsage;
    }

    public BillUsage getCallUsage() {
        return callUsage;
    }

    public void setCallUsage(BillUsage callUsage) {
        this.callUsage = callUsage;
    }

    public BillUsage getSmsUsage() {
        return smsUsage;
    }

    public void setSmsUsage(BillUsage smsUsage) {
        this.smsUsage = smsUsage;
    }
}
