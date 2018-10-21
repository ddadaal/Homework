package database.mapper;

import database.model.Bill;
import database.model.DataUsageType;
import database.model.Plan;

import java.util.Date;
import java.util.List;

public interface MainMapper {

    List<Plan> getAllPlans();

    void orderPlan(String userId, String planId, boolean activateImmediately);

    void cancelPlan(String userId, String planId);

    void generateCallUsage(String userId, Date startDate, Date endDate, double quantity);

    void generateDataUsage(String userId, Date startDate, Date endDate, double quantity, DataUsageType type);

    Bill generateBill();

}
