package database.data;

import database.Main;
import database.model.*;
import database.model.usage.ServiceType;
import database.model.usage.Usage;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainDataService {

    /**
     * 计算插入的usage所需要支付的钱。
     * usage在调用此方法前不应该插入数据库。
     * 由于套餐可能会变化，此数据将不会被插入数据库。
     *
     * @param usage          参数
     * @param basicCost      基础消费
     * @return 花费的钱
     */
    private UsageCharge calculateUsageCharge(Usage usage,
                                             BasicCost basicCost
    ) {
        // existingUsage是按startTime从早到晚排序
        try (SqlSession session = MapperFactory.getSession()) {


            MainMapper mainMapper = session.getMapper(MainMapper.class);
            List<Plan> activePlans= mainMapper.getActivePlans(usage.getUserId(), usage.getStartTime());


            if (usage.getServiceType().equals(ServiceType.CALL)) {

                List<Usage> usages = mainMapper.getUsages(usage.getUserId(), usage.getStartTime(), ServiceType.CALL);
                List<Plan> activePlansWithCalling = activePlans.stream().filter(x -> x.getCallMinutes() > 0).collect(Collectors.toList());




                double limit = 0;
                double extra = 0;
                for (Usage u : usages) {
                    limit = activePlans.stream().mapToInt(Plan::getCallMinutes).sum();
                }
            }
        }

    }

    /**
     * 插入Usage
     *
     * @param usage
     * @return 本Usage所花费的话费。
     */
    public double addUsage(Usage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            List<Plan> plans = mapper.getActivePlans(usage.getUserId(), usage.getStartTime());
            List<Usage> existingUsages = mapper.getUsages(usage.getUserId(), usage.getStartTime(), usage.getServiceType());
            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());
            UsageCharge charge = calculateUsageCharge(usage, basicCost);

            mapper.addUsage(usage.getUserId(), usage.getStartTime(), usage.getAmount(), usage.getServiceType());

            return charge.getCharge();
        }
    }

    /**
     * 获得所有可用套餐
     *
     * @return 所有可用套餐
     */
    public List<Plan> getAllPlans() {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.getAllPlans();
        }
    }


    /**
     * 订阅套餐
     *
     * @param userId              用户ID
     * @param planId              套餐ID
     * @param activateImmediately 立即生效？若为false，则为下月生效
     * @return 交易ID
     */
    public int orderPlan(int userId, int planId, boolean activateImmediately) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.orderPlan(userId, planId, activateImmediately);
        }
    }

    /**
     * 取消套餐
     *
     * @param transactionId       订阅此套餐的交易ID
     * @param activateImmediately 立即生效？若为false，则为下月生效
     */
    public void cancelPlan(int transactionId, boolean activateImmediately) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            mapper.cancelPlan(transactionId, activateImmediately);
        }
    }

    /**
     * 获得指定日期所在月份的账单。
     * 如果是当前月份，就是当目前为止的时间；若为以前月份，则为当月最后一天的23:59:59:99999的账单
     *
     * @param userId   用户ID
     * @param datetime 日期。只会使用date的年和月部分。
     * @return 账单
     */
    public Bill generateBill(int userId, LocalDateTime datetime) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            Bill bill = new Bill();

            // get date range for this query
            LocalDateTime startDate = YearMonth.from(datetime).atDay(1).atStartOfDay();

            LocalDateTime endDate = YearMonth.from(datetime).equals(YearMonth.now())
                ? datetime
                : YearMonth.from(datetime).atEndOfMonth().atTime(LocalTime.MAX);


            bill.setStartDate(startDate);

            bill.setEndDate(endDate);


            // set user information
            User user = mapper.getUser(userId);


            bill.setUserId(userId);
            bill.setName(user.getName());

            // set basic cost

            BasicCost basicCost = mapper.getBasicCost(user.getBasicCostId());

            bill.setBasicCost(basicCost);

            // set active plans

            List<Plan> activePlans = mapper.getActivePlans(userId, endDate);

            bill.setActivePlanList(activePlans);

            // 设置手机流量使用量

            List<Usage> callUsages = mapper.getUsages(userId, endDate, ServiceType.CALL);





            return bill;


        }
    }

    /**
     * 获得当前月份的账单。
     *
     * @param userId 用户ID
     * @return 账单
     */
    public Bill generateBill(int userId) {
        return this.generateBill(userId, LocalDateTime.now());
    }

}
