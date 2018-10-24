package database.test;


import database.data.MainDataService;
import database.example.InitExample;
import database.model.Bill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;


public class MainTest {
    public static final double DELTA = 0.001;

    MainDataService service = new MainDataService();

    @BeforeAll
    public static void beforeAll() {
        InitExample.insertInitData();
    }

    @Test
    void tryOrderPlan() {
        // 目前用户2有200分钟的电话；需要支付的话费为90元。
        Bill bill1 = service.generateBill(2);
        assertEquals(200, bill1.getCallUsage().getLimit(), DELTA);
        assertEquals(90, bill1.getTotalCharge(), DELTA);

        //用户2订阅一个套餐1（20块），立刻生效；再订阅一个套餐1，下月生效
        int tid1 = service.orderPlan(2, 1, true);
        int tid2 = service.orderPlan(2, 1, false);

        // 现在用户有300分钟的电话，需要支付的话费为110元；有效的套餐有3个。
        Bill bill2 = service.generateBill(2);
        assertEquals(300, bill2.getCallUsage().getLimit(), DELTA);
        assertEquals(110, bill2.getTotalCharge(), DELTA);
        assertEquals(3, bill2.getActivePlanList().size());

        // 下月用户有400分钟电话，需要支付的话费为130;有效的套餐有4个。
        Bill bill3 = service.generateBill(2, YearMonth.now().plusMonths(1).atEndOfMonth().atTime(LocalTime.MIN));
        assertEquals(400, bill3.getCallUsage().getLimit(), DELTA);
        assertEquals(130, bill3.getTotalCharge(), DELTA);
        assertEquals(4, bill3.getActivePlanList().size());
    }
}
