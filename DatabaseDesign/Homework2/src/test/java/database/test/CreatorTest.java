package database.test;

import database.data.CreatorMapper;
import database.data.MainDataService;
import database.data.MainMapper;
import database.data.MapperFactory;
import database.model.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

class CreatorTest {

    public static final double DELTA = 0.001;

    MainDataService service = new MainDataService();

    @Test
    void resetTables() {
        MapperFactory.processMapper(CreatorMapper.class, mapper -> {
            mapper.dropAllTables();
            mapper.createAllTables();
        });
    }

    @Test
    void insertSomeTestData() {
        try (SqlSession session = MapperFactory.getSession()) {

            CreatorMapper creatorMapper = session.getMapper(CreatorMapper.class);
            MainMapper mainMapper = session.getMapper(MainMapper.class);

            creatorMapper.dropAllTables();
            creatorMapper.createAllTables();

            Plan plan1 = new Plan("话费套餐", 20, 100, 0, 0, 0);
            Plan plan2 = new Plan("短信套餐", 10, 0, 200, 0, 0);
            Plan plan3 = new Plan("本地流量套餐", 20, 0, 0, 2048 * 1024, 0);
            Plan plan4 = new Plan("国内流量套餐", 30, 0, 0, 0, 2048 * 1024);
            Plan plan5 = new Plan("霸王套餐", 70, 100, 200, 2048 * 1024, 2048 * 1024);

            creatorMapper.insertPlan(plan1);
            creatorMapper.insertPlan(plan2);
            creatorMapper.insertPlan(plan3);
            creatorMapper.insertPlan(plan4);
            creatorMapper.insertPlan(plan5);

            BasicCost basicCost = new BasicCost(0.5, 0.1, 2, 5);
            creatorMapper.insertBasicCost(basicCost);

            User user1 = new User("测试用户1", basicCost.getId());
            User user2 = new User("测试用户2", basicCost.getId());

            creatorMapper.insertUser(user1);
            creatorMapper.insertUser(user2);


            // 用户1订阅2, 3, 4套餐，2下月生效，3，4立刻生效
            mainMapper.orderPlan(user1.getId(), plan2.getId(), false);
            mainMapper.orderPlan(user1.getId(), plan3.getId(), true);
            mainMapper.orderPlan(user1.getId(), plan4.getId(), true);

            // 用户2订阅1,5套餐，都立刻生效
            mainMapper.orderPlan(user2.getId(), plan1.getId(), true);
            mainMapper.orderPlan(user2.getId(), plan5.getId(), true);


            // 用户1打了300秒电话，算7分钟；发了一条短信；用了3G省内流量，512M国内流量
            mainMapper.addCallUsage(user1.getId(), Instant.now(), Instant.now().plus(300, ChronoUnit.SECONDS), 7);
            mainMapper.addSmsUsage(user1.getId(), Instant.now());
            mainMapper.addDataUsage(user1.getId(), Instant.now(), Instant.now().plus(300, ChronoUnit.MINUTES), 3072*1024, DataUsageType.LOCAL);
            mainMapper.addDataUsage(user1.getId(), Instant.now(), Instant.now().plus(300, ChronoUnit.MINUTES), 512*1024, DataUsageType.DOMESTIC);

            LocalDateTime now = LocalDateTime.now();
            // 计算账单并判断正确性
            Bill bill = service.generateBill(user1.getId(), now);

            assertEquals(1, bill.getUserId());
            assertEquals("测试用户1", bill.getName());
            assertEquals(YearMonth.from(LocalDateTime.now()).atDay(1).atStartOfDay(), bill.getStartDate());
            assertEquals(now, bill.getEndDate());
            assertEquals(2, bill.getActivePlanList().size());
            assertEquals(basicCost.getId(), bill.getBasicCost().getId());

            assertEquals(new BillUsage(7, 7, 7 * basicCost.getCallCost()), bill.getCallUsage());
            assertEquals(new BillUsage(1, 1,1 * basicCost.getSmsCost()), bill.getSmsUsage());
            assertEquals(new BillUsage(2048*1024, 0, 0), bill.getLocalDataUsage());
            assertEquals(new BillUsage((1024+512)*1024, 0, 0), bill.getDomesticDataUsage());

            assertEquals(7*basicCost.getCallCost() + 1*basicCost.getSmsCost(), bill.getTotalCharge(), DELTA);

//            // 用户2打了202分钟电话；用了3096M省内流量
//            mainMapper.addCallUsage(user2.getId(), Instant.now(), Instant.now().plus(201 * 60 + 2, ChronoUnit.SECONDS), 202);
//            mainMapper.addDataUsage(user2.getId(), Instant.now(), Instant.now().plus(201, ChronoUnit.MINUTES), 3096 * 1024, DataUsageType.LOCAL);
        }
    }

}