package database;

import database.data.MainDataService;
import database.example.InitExample;
import database.model.UserPlan;
import database.model.UserPlanTransaction;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    MainDataService service = new MainDataService();

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();

        InitExample.insertInitData();
        main.orderPlan();



    }

    void countTime(String title, Runnable action ){
        System.out.println(title);
        LocalDateTime startTime = LocalDateTime.now();
        action.run();
        LocalDateTime endTime = LocalDateTime.now();

        Duration duration = Duration.between(startTime, endTime);

        System.out.println();
        System.out.println("开始时间：" + startTime);
        System.out.println("结束时间：" + endTime);
        System.out.println("持续时间：" + duration.toMillis() + " ms");
    }

    <T> void printAll(List<T> list) {
        printAll(list, false);
    }

    <T> void printAll(List<T> list, boolean reverse) {
        for (int i=0;i<list.size();i++) {
            int j = reverse ? list.size()-1-i: i;
            System.out.println(list.get(j));
        }
    }


    public void queryAllPlans() {
        countTime("1.1 查询所有套餐", () -> {
            printAll(service.getAllPlans());
        });
    }

    public void queryUserPlan() {
        int userId = 1;
        countTime("1.2 查询用户1的套餐订阅记录", () -> {
            List<UserPlan> userPlans = service.getUserPlans(userId);
            printAll(userPlans, true);
        });
    }

    public void queryUserTransaction() {
        int userId = 1;
        countTime("1.3 查询用户1的套餐交易记录", () -> {
            List<UserPlanTransaction> transactions = service.getTransactions(userId);
            printAll(transactions, true);
        });
    }

    public void orderPlan() throws InterruptedException {
        int userId = 3;

        LocalDateTime t = LocalDateTime.now();

        System.out.println("查看用户目前的通话限额：");
        System.out.println(service.generateBill(userId, t).getCallBill().getLimit());

        System.out.println();

        countTime("2.1.1 用户3订阅立刻生效的套餐1", () -> {
            service.orderPlan(userId, 1, t,true);
        });

        System.out.println();

        countTime("2.1.2 用户3订阅下月生效的套餐1", () -> {
            service.orderPlan(userId,1,t,false);
        });

        System.out.println();
        System.out.println("查询用户3用户套餐记录");

        printAll(service.getUserPlans(userId),true);

        System.out.println();
        System.out.println("查看用户交易记录信息");
        printAll(service.getTransactions(userId), true);

        System.out.println();
        System.out.println("查看用户目前的通话限额：");
        System.out.println(service.generateBill(userId, t).getCallBill().getLimit());
    }
}
