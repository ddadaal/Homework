package database.test;

import database.data.CreatorMapper;
import database.data.MapperFactory;
import database.model.BasicCost;
import database.model.Plan;
import database.model.User;
import org.junit.jupiter.api.Test;

class CreatorTest {

    @Test
    void resetTables() {
        MapperFactory.processMapper(CreatorMapper.class, mapper -> {
            mapper.dropAllTables();
            mapper.createAllTables();
        });
    }

    @Test
    void insertSomeTestData() {
        MapperFactory.processMapper(CreatorMapper.class, mapper -> {

            Plan plan = new Plan("话费套餐", 20, 100, 0, 0, 0);
            mapper.insertPlan(plan);

            BasicCost basicCost = new BasicCost(0.5, 0.1, 2,5);

            mapper.insertBasicCost(basicCost);

            User user = new User("测试用户", basicCost.getId());
            mapper.insertUser(user);

        });
    }

}