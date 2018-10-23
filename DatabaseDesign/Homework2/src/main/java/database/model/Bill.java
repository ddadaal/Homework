package database.model;

import java.util.Date;
import java.util.List;

public class Bill {
    // basic information
    private String userId;
    private String name;

    // bill date range
    private Date startDate;
    private Date endDate;

    // applied basic cost
    private BasicCost basicCost;

    // ordered plans
    private List<Plan> planList;

    // total usage
    private double totalLocalDataUsage;
    private double totalDomesticDataUsage;
    private int totalCallUsage;
    private int totalSmsUsage;

    // extra usage, aka usage outside plans
    private double extraLocalDataUsage;
    private double extraDomesticDataUsage;
    private int extraCallUsage;
    private int extraSmsUsage;

    // extra fees
    private double extraCallFee;
    private double extraSmsFee;
    private double extraLocalDataFee;
    private double extraDomesticFee;

    // total fees
    private double totalFee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BasicCost getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(BasicCost basicCost) {
        this.basicCost = basicCost;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public double getTotalLocalDataUsage() {
        return totalLocalDataUsage;
    }

    public void setTotalLocalDataUsage(double totalLocalDataUsage) {
        this.totalLocalDataUsage = totalLocalDataUsage;
    }

    public double getTotalDomesticDataUsage() {
        return totalDomesticDataUsage;
    }

    public void setTotalDomesticDataUsage(double totalDomesticDataUsage) {
        this.totalDomesticDataUsage = totalDomesticDataUsage;
    }

    public int getTotalCallUsage() {
        return totalCallUsage;
    }

    public void setTotalCallUsage(int totalCallUsage) {
        this.totalCallUsage = totalCallUsage;
    }

    public int getTotalSmsUsage() {
        return totalSmsUsage;
    }

    public void setTotalSmsUsage(int totalSmsUsage) {
        this.totalSmsUsage = totalSmsUsage;
    }

    public double getExtraLocalDataUsage() {
        return extraLocalDataUsage;
    }

    public void setExtraLocalDataUsage(double extraLocalDataUsage) {
        this.extraLocalDataUsage = extraLocalDataUsage;
    }

    public double getExtraDomesticDataUsage() {
        return extraDomesticDataUsage;
    }

    public void setExtraDomesticDataUsage(double extraDomesticDataUsage) {
        this.extraDomesticDataUsage = extraDomesticDataUsage;
    }

    public int getExtraCallUsage() {
        return extraCallUsage;
    }

    public void setExtraCallUsage(int extraCallUsage) {
        this.extraCallUsage = extraCallUsage;
    }

    public int getExtraSmsUsage() {
        return extraSmsUsage;
    }

    public void setExtraSmsUsage(int extraSmsUsage) {
        this.extraSmsUsage = extraSmsUsage;
    }

    public double getExtraCallFee() {
        return extraCallFee;
    }

    public void setExtraCallFee(double extraCallFee) {
        this.extraCallFee = extraCallFee;
    }

    public double getExtraSmsFee() {
        return extraSmsFee;
    }

    public void setExtraSmsFee(double extraSmsFee) {
        this.extraSmsFee = extraSmsFee;
    }

    public double getExtraLocalDataFee() {
        return extraLocalDataFee;
    }

    public void setExtraLocalDataFee(double extraLocalDataFee) {
        this.extraLocalDataFee = extraLocalDataFee;
    }

    public double getExtraDomesticFee() {
        return extraDomesticFee;
    }

    public void setExtraDomesticFee(double extraDomesticFee) {
        this.extraDomesticFee = extraDomesticFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
}
