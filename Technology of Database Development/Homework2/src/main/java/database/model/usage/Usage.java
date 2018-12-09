package database.model.usage;

import javax.xml.ws.Service;
import java.time.LocalDateTime;

public class Usage {
    private int userId;
    private double amount;
    private LocalDateTime startTime;
    private ServiceType serviceType;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Usage() {
    }

    public Usage(int userId, double amount, LocalDateTime startTime, ServiceType serviceType) {
        this.userId = userId;
        this.amount = amount;
        this.startTime = startTime;
        this.serviceType = serviceType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
