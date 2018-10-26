package database.model;

import database.model.usage.ServiceType;

import java.time.LocalDateTime;

public class Plan {
    private int id;
    private String name;
    private double cost;
    private int callMinutes;
    private int sms;
    private double localData;
    private double domesticData;

    public Plan(String name, double cost, int callMinutes, int sms, double localData, double domesticData) {
        this.name = name;
        this.cost = cost;
        this.callMinutes = callMinutes;
        this.sms = sms;
        this.localData = localData;
        this.domesticData = domesticData;
    }

    @Override
    public String toString() {
        return "套餐{" +
            "id=" + id +
            ", 名称='" + name + '\'' +
            ", 资费（元）=" + cost +
            ", 通话时长（分钟）=" + callMinutes +
            ", 短信（条）=" + sms +
            ", 本地流量（每M）=" + localData +
            ", 国内流量（每M）=" + domesticData +
            '}';
    }

    public Plan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCallMinutes() {
        return callMinutes;
    }

    public void setCallMinutes(int callMinutes) {
        this.callMinutes = callMinutes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public double getLocalData() {
        return localData;
    }

    public void setLocalData(double localData) {
        this.localData = localData;
    }

    public double getDomesticData() {
        return domesticData;
    }

    public void setDomesticData(double domesticData) {
        this.domesticData = domesticData;
    }

    public double getLimitByServiceType(ServiceType serviceType) {
        switch (serviceType) {
            case SMS:
                return sms;
            case CALL:
                return callMinutes;
            case LOCAL_DATA:
                return localData;
            case DOMESTIC_DATA:
                return domesticData;
        }
        return 0;
    }


}
