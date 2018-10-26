package database;

import database.data.MainDataService;
import database.example.InitExample;
import database.model.Bill;
import database.model.UserPlan;
import database.model.UserPlanTransaction;
import database.model.usage.ServiceType;
import database.model.usage.Usage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    MainDataService service = new MainDataService();

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();

        InitExample.insertInitData();
        main.generateBill();


    }

    private void countTime(String title, Runnable action) {
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

    private <T> void printAll(List<T> list) {
        printAll(list, false);
    }

    private <T> void printAll(List<T> list, boolean reverse) {
        for (int i = 0; i < list.size(); i++) {
            int j = reverse ? list.size() - 1 - i : i;
            System.out.println(list.get(j));
        }
    }


    public void queryAllPlans() {
        countTime("1.1 查询所有套餐", () -> {
            printAll(service.getAllPlans());
        });
    }

    public void queryActivePlans() {
        int userId = 2;

        LocalDateTime t = LocalDateTime.now().plusSeconds(2);
        countTime("1.2 查询用户2的目前生效的订阅记录", () -> {
            List<UserPlan> userPlans = service.getActivePlans(userId, t);
            printAll(userPlans, true);
        });
    }

    public void queryUserPlanSubscriptions() {
        int userId = 2;
        countTime("1.3 查询用户2的套餐订阅记录（包含生效的和已取消的）", () -> {
            List<UserPlan> userPlans = service.getUserPlans(userId);
            printAll(userPlans, true);
        });
    }

    public void queryUserTransactions() {
        int userId = 2;
        countTime("1.4 查询用户2的套餐交易记录", () -> {
            List<UserPlanTransaction> transactions = service.getTransactions(userId);
            printAll(transactions, true);
        });
    }

    public void orderPlan() {
        int userId = 3;

        LocalDateTime t = LocalDateTime.now();

        System.out.println("查看用户目前的通话限额和短信限额（证明支持一个套餐有多个优惠项目）：");
        Bill bill1 = service.generateBill(userId, t);
        System.out.println("通话：" + bill1.getCallBill().getLimit() + "，短信：" + bill1.getSmsBill().getLimit());
        System.out.println();

        countTime("2.1 用户3订阅立刻生效的套餐1（100分钟电话）", () -> {
            service.orderPlan(userId, 1, t, true);
        });

        System.out.println();
        countTime("2.2 用户3订阅立刻生效的套餐5 （霸王套餐，有100分钟的电话，200条短信，2G本地流量和2G国内流量）", () -> {
            service.orderPlan(userId, 5, t, true);
        });

        System.out.println();

        countTime("2.3 用户3订阅下月生效的套餐1（100分钟电话）", () -> {
            service.orderPlan(userId, 1, t, false);
        });

        System.out.println();
        System.out.println("查询用户3用户套餐记录");

        printAll(service.getUserPlans(userId), true);

        System.out.println();
        System.out.println("查看用户交易记录信息");
        printAll(service.getTransactions(userId), true);

        System.out.println();
        System.out.println("查看用户目前的通话限额和短信限额（证明支持一个套餐有多个优惠项目）：");
        Bill bill2 = service.generateBill(userId, t.plusSeconds(1));
        System.out.println("通话：" + bill2.getCallBill().getLimit() + "，短信：" + bill2.getSmsBill().getLimit());
    }

    public void cancelPlan() {
        int userId = 3;

        LocalDateTime t = LocalDateTime.now();

        int tid1 = service.orderPlan(userId, 2, t, true);
        int tid2 = service.orderPlan(userId, 2, t, true);

        System.out.println("用户3刚才订阅了两个短信套餐。");
        System.out.println();

        System.out.println("查看用户3目前的短信限额：");
        System.out.println(service.generateBill(userId, t).getSmsBill().getLimit());

        System.out.println();

        countTime("立即退订用户3的第一个短信套餐。", () -> {
            service.cancelPlan(tid1, t, true);
        });
        System.out.println();

        System.out.println("查看用户3目前的短信限额：");
        System.out.println(service.generateBill(userId, t.plusSeconds(1)).getSmsBill().getLimit());
        System.out.println();

        countTime("下月退订用户3的第一个短信套餐。", () -> {
            service.cancelPlan(tid2, t, false);
        });
        System.out.println();

        System.out.println("查看用户3目前的短信限额：");
        System.out.println(service.generateBill(userId, t).getSmsBill().getLimit());

        System.out.println();
        System.out.println("查询用户3用户套餐记录");

        printAll(service.getUserPlans(userId), true);

        System.out.println();
        System.out.println("查看用户交易记录信息");
        printAll(service.getTransactions(userId), true);

    }

    public void addCallUsage() {
        LocalDateTime t = LocalDateTime.now();

        int userId = 3;
        System.out.println("用户3是全新的，没有订阅任何套餐，没有任何使用量。");

        System.out.println();
        countTime("用户3在没有订阅任何套餐的时候打了10分钟电话，将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 10, t, ServiceType.CALL));
        });

        System.out.println();
        System.out.println("查看此时用户的电话服务账单");
        System.out.println(service.generateBill(userId, t.plusSeconds(1)).getCallBill());

        System.out.println();
        System.out.println("用户在以上电话的1s之后，订阅了套餐1（100分钟电话）");
        service.orderPlan(userId, 1, t.plusSeconds(1), true);

        System.out.println();
        countTime("用户3在订阅套餐1的1秒后时候打了10分钟电话（包含在套餐中，不额外计费），将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 10, t.plusSeconds(2), ServiceType.CALL));
        });

        System.out.println();
        System.out.println("查看此时用户的电话服务账单");
        System.out.println(service.generateBill(userId, t.plusSeconds(3)).getCallBill());

        System.out.println();
        countTime("用户3在打完刚才10分钟的电话的1秒后时候又打了200分钟电话（套餐只剩90，对另外的110分钟额外计费），将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 200, t.plusMinutes(10).plusSeconds(4), ServiceType.CALL));
        });

        System.out.println();
        System.out.println("查看用户的电话服务账单");
        System.out.println(service.generateBill(userId, t.plusMinutes(10).plusSeconds(4)).getCallBill());
    }

    public void addDataUsage() {
        LocalDateTime t = LocalDateTime.now();

        int userId = 3;
        System.out.println("用户3是全新的，没有订阅任何套餐，没有任何使用量。");

        System.out.println();
        countTime("用户3在没有订阅任何套餐的时候使用了10M本地流量，将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 10, t, ServiceType.LOCAL_DATA));
        });

        System.out.println();
        System.out.println("查看此时用户的流量服务账单");
        Bill bill1 = service.generateBill(userId, t.plusSeconds(1));
        System.out.println("本地流量：" + bill1.getLocalDataBill());
        System.out.println("省内流量：" + bill1.getDomesticDataBill());

        System.out.println();
        System.out.println("用户3在使用了10M本地流量的1s后，订阅了3（2G本地流量）, 5套餐（100分钟电话，200条短信，2G本地流量，2G国内流量）");
        service.orderPlan(userId, 3, t.plusSeconds(1), true);
        service.orderPlan(userId, 5, t.plusSeconds(1), true);

        System.out.println();
        System.out.println("查看此时用户的流量服务账单");
        Bill bill2 = service.generateBill(userId, t.plusSeconds(2));
        System.out.println("本地流量：" + bill2.getLocalDataBill());
        System.out.println("省内流量：" + bill2.getDomesticDataBill());

        System.out.println();
        countTime("用户3在订阅了套餐的1s后，使用了1G国内流量（使用套餐内的1G国内流量，不多余扣费），将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 1024, t.plusSeconds(3), ServiceType.DOMESTIC_DATA));
        });

        System.out.println();
        System.out.println("查看此时用户的流量服务账单");
        Bill bill3 = service.generateBill(userId, t.plusSeconds(4));
        System.out.println("本地流量：" + bill3.getLocalDataBill());
        System.out.println("省内流量：" + bill3.getDomesticDataBill());

        System.out.println();
        countTime("用户3在以上使用1s后，使用了4.5G本地流量（使用完 全部省内流量，又使用了0.5G国内流量，不多余扣费），将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 4.5*1024, t.plusSeconds(4), ServiceType.LOCAL_DATA));
        });

        System.out.println();
        System.out.println("查看此时用户的流量服务账单");
        Bill bill4 = service.generateBill(userId, t.plusSeconds(5));
        System.out.println("本地流量：" + bill4.getLocalDataBill());
        System.out.println("省内流量：" + bill4.getDomesticDataBill());

        System.out.println();
        countTime("用户3在以上使用1s后，又使用了1G本地流量（使用了剩余0.5G国内流量，多使用了0.5G本地流量，多交0.5G本地流量的资费），将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 1024, t.plusSeconds(5), ServiceType.LOCAL_DATA));
        });

        System.out.println();
        System.out.println("查看此时用户的流量服务账单");
        Bill bill5 = service.generateBill(userId, t.plusSeconds(6));
        System.out.println("本地流量：" + bill5.getLocalDataBill());
        System.out.println("省内流量：" + bill5.getDomesticDataBill());


        System.out.println();
        countTime("用户3在以上使用1s后，又使用了1G国内流量（省内和国内都已经用完，1G流量均为额外国内流量），将此使用情况记入数据库", () -> {
            service.addUsage(new Usage(userId, 1024, t.plusSeconds(7), ServiceType.DOMESTIC_DATA));
        });

        System.out.println();
        System.out.println("查看此时用户的流量服务账单");
        Bill bill6 = service.generateBill(userId, t.plusSeconds(8));
        System.out.println("本地流量：" + bill6.getLocalDataBill());
        System.out.println("省内流量：" + bill6.getDomesticDataBill());


    }

    void generateBill() {
        // 初始条件：
        // 用户1订阅2, 3, 4套餐，2下月生效，3，4立刻生效（即用户1拥有2G国内流量，2G省内流量，（200条短信下月生效））
        // 用户1打了7分钟电话；发了1条短信；用了3G省内流量，1.5G国内流量

        System.out.println("初始条件：");
        System.out.println("用户1订阅2, 3, 4套餐，2下月生效，3，4立刻生效（即用户1拥有2G国内流量，2G省内流量，（200条短信下月生效））");
        System.out.println("用户1打了7分钟电话；发了1条短信；用了3G省内流量，1.5G国内流量");

        // 基础条件里以下初始条件是在2s做出的。所以这里查3秒后的账单
        LocalDateTime t = LocalDateTime.now().plusSeconds(3);

        System.out.println();
        countTime("用户1的账单（此处开始计时）", () -> {
            Bill bill = service.generateBill(1, t);
            System.out.println(bill);
        });

    }
}
