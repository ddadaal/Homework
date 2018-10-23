package database.model;

import java.util.Date;

public class FreeLimit {
    private int userId;
    private Date queryTime;

    private int call;
    private int sms;
    private double localData;
    private double domesticData;

    public FreeLimit() {
    }
}
