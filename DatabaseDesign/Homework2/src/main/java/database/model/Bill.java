package database.model;

import java.util.Date;

public class Bill {
    private String userId;
    private Date startDate;
    private Date endDate;
    private double totalLocalDataUsage;
    private double totalDomesticDataUsage;
    private double totalCallUsage;
    private double exceedingLocalDataUsage;
    private double exceedingDomesticDataUsage;
    private double exceedingCallUsage;
    private long fee;
}
