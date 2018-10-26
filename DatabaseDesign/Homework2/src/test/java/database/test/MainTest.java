package database.test;


import database.data.CreatorMapper;
import database.data.MainDataService;
import database.data.MainMapper;
import database.data.MapperFactory;
import database.example.InitExample;
import database.model.Bill;
import database.model.ServiceBill;
import database.model.User;
import database.model.usage.ServiceType;
import database.model.usage.Usage;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

import static org.junit.Assert.assertEquals;


public class MainTest {
    public static final double DELTA = 0.001;

    MainDataService service = new MainDataService();

    int userId = 4;

    @BeforeEach
    public void beforeClass() {
        InitExample.insertInitData();

        LocalDateTime baseTime = LocalDateTime.now();

        // 插入一个用户4,订1，5套餐
        try (SqlSession session = MapperFactory.getSession()) {
            CreatorMapper creatorMapper = session.getMapper(CreatorMapper.class);
            MainMapper mainMapper = session.getMapper(MainMapper.class);
            User user = new User("测试用户4", 1, "123!23");



            creatorMapper.insertUser(user);
            userId = user.getId();

            int tid = mainMapper.orderPlan(user.getId(), 1, baseTime, true);
            mainMapper.orderPlan(user.getId(), 5, baseTime, true);
        }

    }

    private Bill assertUser4Valid() {
        // 目前用户4定了1,5套餐，有200分钟的电话，200条短信，2G省内流量，2G国内流量；需要支付的话费为90元。
        LocalDateTime t = LocalDateTime.now().plusSeconds(1);
        Bill bill1 = service.generateBill(4, t);
        assertEquals(200, bill1.getCallBill().getLimit(), DELTA);
        assertEquals(200, bill1.getSmsBill().getLimit(), DELTA);
        assertEquals(2048, bill1.getLocalDataBill().getLimit(), DELTA);
        assertEquals(2048, bill1.getDomesticDataBill().getLimit(), DELTA);
        assertEquals(90, bill1.getTotalCharge(), DELTA);
        return bill1;
    }

    @Test
    void tryOrderPlan() {

        Bill bill1 = assertUser4Valid();

        LocalDateTime t = LocalDateTime.now().plusSeconds(1);

        //用户4订阅一个套餐1（20块），立刻生效；再订阅一个套餐1，下月生效
        int tid1 = service.orderPlan(userId, 1, t,true);
        int tid2 = service.orderPlan(userId, 1, t,false);

        // 现在用户有300分钟的电话，需要支付的话费为110元；有效的套餐有3个。
        Bill bill2 = service.generateBill(userId, t.plusSeconds(1));
        assertEquals(300, bill2.getCallBill().getLimit(), DELTA);
        assertEquals(110, bill2.getTotalCharge(), DELTA);
        assertEquals(3, bill2.getActivePlanList().size());

        // 下月用户有400分钟电话，需要支付的话费为130;有效的套餐有4个。
        Bill bill3 = service.generateBill(userId, YearMonth.from(t).plusMonths(1).atEndOfMonth().atTime(LocalTime.MAX));
        assertEquals(400, bill3.getCallBill().getLimit(), DELTA);
        assertEquals(130, bill3.getTotalCharge(), DELTA);
        assertEquals(userId, bill3.getActivePlanList().size());
    }

    @Test
    void testOrderPlanAfterUse() {
        Bill bill1 = assertUser4Valid();

        // 发了201条短信。
        LocalDateTime baseTime = LocalDateTime.now();
        service.addUsage(new Usage(userId, 201, baseTime, ServiceType.SMS));

        // 在之后订阅1个2套餐（200条短信）
        service.orderPlan(userId, 2, baseTime.plusSeconds(1), true);

        // 仍然需要交之前那1条多出的短信的钱。
        Bill bill2 = service.generateBill(userId, baseTime.plusSeconds(2));

        assertEquals(1, bill2.getSmsBill().getExtra(), DELTA);

    }

    @Test
    void testAComplexSituation() {
        // 用户3什么都没定。

        LocalDateTime baseTime = LocalDateTime.now();
        // 定2个本地流量（2G）套餐。

        int tid1 = service.orderPlan(3, 3, baseTime, true);
        int tid2 = service.orderPlan(3, 3, baseTime, true);

        // T+1 s用2.5G省内流量
        service.addUsage(new Usage(3, 2.5*1024, baseTime.plusSeconds(1), ServiceType.LOCAL_DATA));

        // T+2 s用1G国内流量
        service.addUsage(new Usage(3, 1024, baseTime.plusSeconds(2), ServiceType.DOMESTIC_DATA));

        // 看T+3 s时刻的账单。应该用了2.5G省内流量（限额4G），超出1G国内流量
        Bill bill2 = service.generateBill(3, baseTime.plusSeconds(3));
        assertEquals(new ServiceBill(2.5*1024, 0,0,4*1024, 1.5*1024), bill2.getLocalDataBill());
        assertEquals(new ServiceBill(1024, 1024, 1024*5, 0, 0), bill2.getDomesticDataBill());

        // 在T+4 s时，退订第一个省内流量套餐！
        service.cancelPlan(tid1, baseTime.plusSeconds(4), true);

        // 看T+5 s时的账单。应该用了2.5G省内流量（限额2G），有资费产生！
        Bill bill3 = service.generateBill(3, baseTime.plusSeconds(5));
        assertEquals(new ServiceBill(2.5*1024, 512,512*2,2*1024, 0), bill3.getLocalDataBill());

        // T+6 s退订第二个省内流量套餐，但是下月生效！
        service.cancelPlan(tid2, baseTime.plusSeconds(6), false);

        // 看T+7 s时的账单。省内资费和bill3应该是一样的。
        Bill bill4 = service.generateBill(3, baseTime.plusSeconds(7));
        assertEquals(new ServiceBill(2.5*1024, 512,512*2,2*1024, 0), bill4.getLocalDataBill());

        // T+7 s时的资费应该为1个套餐3的资费20 + 国内流量超出资费1024*5 + 本地流量超出资费 512 *2
        assertEquals(20 + 1024*5 + 512*2, bill4.getTotalCharge(), DELTA);

    }
}
