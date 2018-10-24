package database.data;

import database.model.*;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

public class MainDataService {

    /**
     * 获得所有可用套餐
     * @return 所有可用套餐
     */
    public List<Plan> getAllPlans() {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.getAllPlans();
        }
    }

    /**
     * 增加短信使用，计算资费
     * @param usage 短信使用对象
     * @return 产生的资费
     */

    public double addSmsUsage(SmsUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());

            double remaining = mapper.getSmsUsage(usage.getUserId(), LocalDateTime.from(usage.getTime())).getRemaining();

            mapper.addSmsUsage(usage.getUserId(), usage.getTime());


            if (remaining <= 1) {
                return basicCost.getSmsCost() * 1;
            } else {
                return 0;
            }


        }
    }

    /**
     * 增加打电话使用
     * 资费都是实时计算，不保存的，因为没有人会细致到看每一条短信/每一通电话/每一次流量使用需要花费多少钱！
     * @param usage 电话使用对象
     * @return 产生的资费
     */

    public double addCallUsage(CallUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);



            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());
            double remaining = mapper.getCallUsage(usage.getUserId(), LocalDateTime.from(usage.getStartTime())).getRemaining();

            mapper.addCallUsage(usage.getUserId(), usage.getStartTime(), usage.getEndTime(), usage.getDuration());

            if (remaining < usage.getDuration()){
                return basicCost.getCallCost() * (usage.getDuration() - remaining);
            } else {
                return  0;
            }
        }
    }

    /**
     * 增加流量使用
     * 资费都是实时计算，不保存的，因为没有人会细致到看每一条短信/每一通电话/每一次流量使用需要花费多少钱！
     * @param usage 流量使用
     * @return 产生的资费
     */

    public double addDataUsage(DataUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);



            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());

            if (usage.getDataType().equals(DataType.LOCAL)) {
                double remaining = mapper.getLocalDataUsage(usage.getUserId(), LocalDateTime.from(usage.getStartTime())).getRemaining();

                mapper.addDataUsage(usage.getUserId(), usage.getStartTime(), usage.getEndTime(), usage.getAmount(), usage.getDataType());


                if (remaining < usage.getAmount()) {
                    // 省内流量用光，使用全国流量
                    double remainingDomestic = mapper.getDomesticDataUsage(usage.getUserId(), LocalDateTime.from(usage.getStartTime())).getRemaining();
                    if (remainingDomestic < usage.getAmount() - remaining) {
                        // 都用光，计算流量费
                        return basicCost.getLocalDataCost() * (usage.getAmount() - remaining - remainingDomestic);
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                double remaining = mapper.getDomesticDataUsage(usage.getUserId(), LocalDateTime.from(usage.getStartTime())).getRemaining();


                mapper.addDataUsage(usage.getUserId(), usage.getStartTime(), usage.getEndTime(), usage.getAmount(), usage.getDataType());


                if (remaining < usage.getAmount()) {
                    return basicCost.getDomesticDataCost() * (usage.getAmount() - remaining);
                } else {
                    return 0;
                }
            }



        }
    }

    /**
     * 订阅套餐
     * @param userId 用户ID
     * @param planId 套餐ID
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
     * @param transactionId 订阅此套餐的交易ID
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
     * @param userId 用户ID
     * @param month 日期。只会使用date的年和月部分。
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

            // set usages

            Usage callUsage = mapper.getCallUsage(userId, endDate);

            Usage smsUsage = mapper.getSmsUsage(userId, endDate);

            Usage localDataUsage = mapper.getLocalDataUsage(userId, endDate);

            Usage domesticDataUsage = mapper.getDomesticDataUsage(userId, endDate);

            bill.setCallUsage(BillUsage.fromUsage(callUsage, basicCost.getCallCost()));

            bill.setSmsUsage(BillUsage.fromUsage(smsUsage, basicCost.getSmsCost()));


            // 计算流量费用
            if (localDataUsage.getRemaining() > 0) {
                // 本地流量还剩余，说明本地流量还没有使用完全，不会有本地流量使用全国流量的情况
                bill.setLocalDataUsage(BillUsage.fromUsage(localDataUsage, basicCost.getLocalDataCost()));
                bill.setDomesticDataUsage(BillUsage.fromUsage(domesticDataUsage, basicCost.getDomesticDataCost()));

            } else {
                // 本地流量使用完毕，接下来会继续使用全国流量

                // 多使用的全国流量
                double extraLocalData = localDataUsage.getTotal() - localDataUsage.getLimit();
                if (extraLocalData > domesticDataUsage.getRemaining()) {
                    // 剩余的全国流量也不够用，那么剩下来的流量会按照国内流量的标准支付
                    double extraData = extraLocalData - domesticDataUsage.getRemaining();
                    bill.setLocalDataUsage(new BillUsage(
                        localDataUsage.getLimit(),
                        extraData,
                        extraData* basicCost.getLocalDataCost(),
                        localDataUsage.getLimit()
                    ));
                    // 全国流量的使用多增加extraData，但是在计算超出部分时，不计算这个数值，根据真正超出的全国流量的量计算全国流量的总额
                    double extraDomesticData = domesticDataUsage.getTotal() > domesticDataUsage.getLimit()
                        ? domesticDataUsage.getTotal() - domesticDataUsage.getLimit()
                        : 0;
                    bill.setDomesticDataUsage(new BillUsage(
                        domesticDataUsage.getTotal() + extraData,
                        extraDomesticData,
                        extraDomesticData * basicCost.getDomesticDataCost(),
                        domesticDataUsage.getLimit()
                    ));

                } else {
                    // 剩余的全国流量够用
                    bill.setLocalDataUsage(new BillUsage(
                        localDataUsage.getLimit(),
                        0,
                        0,
                        localDataUsage.getLimit()
                    ));
                    bill.setDomesticDataUsage(new BillUsage(
                        domesticDataUsage.getTotal() + extraLocalData,
                        0,
                        0,
                        domesticDataUsage.getLimit()
                    ));
                }

            }



            return bill;


        }
    }

    /**
     * 获得当前月份的账单。
     * @param userId 用户ID
     * @return 账单
     */
    public Bill generateBill(int userId) {
        return this.generateBill(userId, LocalDateTime.now());
    }

}
