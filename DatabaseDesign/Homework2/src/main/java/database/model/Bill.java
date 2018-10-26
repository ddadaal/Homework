package database.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {
    // basic information
    private int userId;
    private String name;
    private String phoneNumber;

    // bill date range
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // applied basic cost
    private BasicCost basicCost;

    // active plans
    private List<UserPlan> activePlanList;

    // usages
    private ServiceBill localDataBill;
    private ServiceBill domesticDataBill;
    private ServiceBill callBill;
    private ServiceBill smsBill;

    // total fees
    public double getTotalCharge() {
        return localDataBill.getCharge()
            + domesticDataBill.getCharge()
            + callBill.getCharge()
            + smsBill.getCharge()
            + activePlanList.stream().mapToDouble(x -> x.getPlan().getCost()).sum();
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<UserPlan> getActivePlanList() {
        return activePlanList;
    }

    public void setActivePlanList(List<UserPlan> activePlanList) {
        this.activePlanList = activePlanList;
    }

    public ServiceBill getLocalDataBill() {
        return localDataBill;
    }

    public void setLocalDataBill(ServiceBill localDataBill) {
        this.localDataBill = localDataBill;
    }

    public ServiceBill getDomesticDataBill() {
        return domesticDataBill;
    }

    public void setDomesticDataBill(ServiceBill domesticDataBill) {
        this.domesticDataBill = domesticDataBill;
    }

    public ServiceBill getCallBill() {
        return callBill;
    }

    public void setCallBill(ServiceBill callBill) {
        this.callBill = callBill;
    }

    public ServiceBill getSmsBill() {
        return smsBill;
    }

    public void setSmsBill(ServiceBill smsBill) {
        this.smsBill = smsBill;
    }
}
