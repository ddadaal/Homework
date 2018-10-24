package database.test;

import database.data.CreatorMapper;
import database.data.MainDataService;
import database.data.MainMapper;
import database.data.MapperFactory;
import database.example.InitExample;
import database.model.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeClass
    void insertSomeTestData() {
        InitExample.insertInitData();
    }

    @Test
    void assertUser1Bill() {

        try (SqlSession session = MapperFactory.getSession()) {

//            CreatorMapper creatorMapper = session.getMapper(CreatorMapper.class);
//            MainMapper mainMapper = session.getMapper(MainMapper.class);
//
//            User user = mainMapper.getUser(1);
//            BasicCost basicCost = mainMapper.getBasicCost(user.getBasicCostId());
//
//            LocalDateTime now = LocalDateTime.now();
//            // 计算账单并判断正确性
//            Bill bill = service.generateBill(user.getId());
//
//            assertEquals(1, bill.getUserId());
//            assertEquals("测试用户1", bill.getName());
//            assertEquals(YearMonth.from(LocalDateTime.now()).atDay(1).atStartOfDay(), bill.getStartDate());
//            assertEquals(now, bill.getEndDate());
//            assertEquals(2, bill.getActivePlanList().size());
//            assertEquals(basicCost.getId(), bill.getBasicCost().getId());
//
//            assertEquals(new BillUsage(7, 7, 7 * basicCost.getCallCost(), 0), bill.getCallUsage());
//            assertEquals(new BillUsage(1, 1, 1 * basicCost.getSmsCost(), 0), bill.getSmsUsage());
//            assertEquals(new BillUsage(2048, 512, 512 * basicCost.getLocalDataCost(), 2048), bill.getLocalDataUsage());
//            assertEquals(new BillUsage(2048, 0, 0, 2048), bill.getDomesticDataUsage());
//
//            assertEquals(7 * basicCost.getCallCost()
//                    + 1 * basicCost.getSmsCost()
//                    + 512 * basicCost.getLocalDataCost()
//                    + 50,
//                bill.getTotalCharge(), DELTA);
        }
    }

}