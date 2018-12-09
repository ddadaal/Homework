package database.data;

import database.model.BasicCost;
import database.model.Plan;
import database.model.User;

public interface CreatorMapper {
    void createAllTables();

    void insertPlan(Plan plan);

    void insertUser(User user);

    void insertBasicCost(BasicCost basicCost);

    void dropAllTables();
}
