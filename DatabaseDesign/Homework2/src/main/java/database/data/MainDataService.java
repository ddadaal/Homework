package database.data;

import database.model.*;
import database.model.usage.ServiceType;
import database.model.usage.Usage;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;


public class MainDataService {

    public List<UserPlan> getUserPlans(int userId) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.getUserPlans(userId);
        }
    }

    public List<UserPlanTransaction> getTransactions(int userId) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.getTransactions(userId);
        }
    }

    /**
     * 获得某个服务项目的服务账单
     * @param serviceType 服务项目
     * @param userId 用户ID
     * @param endDate 终止时间
     * @param activePlans 生效的套餐
     * @param basicCost 基础资费
     * @return 使用情况
     */
    private ServiceBill getBillUsageByServiceType(ServiceType serviceType,
                                                  int userId,
                                                  LocalDateTime endDate,
                                                  List<UserPlan> activePlans,
                                                  double basicCost
    ) {
        try (SqlSession session = MapperFactory.getSession()) {


            MainMapper mainMapper = session.getMapper(MainMapper.class);

            if (serviceType.equals(ServiceType.CALL) || serviceType.equals(ServiceType.SMS)) {

                List<Usage> usages = mainMapper.getUsages(userId, endDate, serviceType);

                double limit = activePlans.stream().mapToDouble(x -> x.getPlan().getLimitByServiceType(serviceType)).sum();

                double remaining = 0;
                double extra = 0;
                double totalUsed = 0;

                int planIndex = 0;
                for (Usage u : usages) {
                    while (planIndex < activePlans.size() && activePlans.get(planIndex).isActivatedAt(u.getStartTime())) {
                        UserPlan plan = activePlans.get(planIndex);
                        remaining += plan.getPlan().getLimitByServiceType(serviceType);
                        planIndex++;
                    }
                    double amount = u.getAmount();
                    totalUsed += amount;
                    if (amount > remaining) {
                        extra += amount - remaining;
                        remaining = 0;
                    } else {
                        remaining -= amount;
                    }
                }

                double charge = extra * basicCost;
                return new ServiceBill(totalUsed, extra, charge, limit);
            } else {
                List<Usage> usages = mainMapper.getUsages(
                    userId,
                    endDate,
                    ServiceType.LOCAL_DATA,
                    ServiceType.DOMESTIC_DATA
                );

                double localDataRemaining = 0;
                double domesticDataRemaining = 0;

                double localDataLimit = activePlans.stream().mapToDouble(x -> x.getPlan().getLocalData()).sum();
                double domesticDataLimit = activePlans.stream().mapToDouble(x -> x.getPlan().getDomesticData()).sum();

                double localDataExtra = 0;
                double domesticDataExtra = 0;

                double localDataTotal = 0;
                double domesticDataTotal = 0;

                int planIndex = 0;
                for (Usage u : usages) {
                    while (planIndex < activePlans.size() && activePlans.get(planIndex).isActivatedAt(u.getStartTime())) {
                        UserPlan plan = activePlans.get(planIndex);

                        localDataRemaining += plan.getPlan().getLocalData();

                        domesticDataRemaining += plan.getPlan().getDomesticData();
                        planIndex++;
                    }
                    double amount = u.getAmount();

                    if (u.getServiceType().equals(ServiceType.LOCAL_DATA)) {
                        localDataTotal += amount;
                        // 这个usage是本地流量，先检测localDataLimit是否够用
                        if (amount > localDataRemaining) {
                            // localDataLimit不够用，先扣除所有localDataLimit，再检测domesticDataLimit是否够用
                            double extraAmount = amount - localDataRemaining;
                            localDataRemaining = 0;
                            if (extraAmount > domesticDataRemaining) {
                                // domesticDataLimit也不够用。将多余的流量加到localDataExtra中，扣除所有的domesticDataLimit。
                                localDataExtra += extraAmount - domesticDataRemaining;
                                domesticDataRemaining = 0;
                            } else {
                                // domesticData够用，去除响应的domesticDataLimit
                                domesticDataRemaining -= extraAmount;
                            }
                        } else {
                            // localData够用。只去除localDataLimit
                            localDataRemaining -= amount;
                        }
                    } else {
                        domesticDataTotal += amount;
                        // 这个usage是国内流量。直接检测domesticDataLimit是否够用。
                        if (amount > domesticDataRemaining) {
                            // 国内流量限额不够用。将多余的流量加到domesticDataExtra中，扣除所有的domesticDataLimit，
                            domesticDataExtra += amount - domesticDataRemaining;
                            domesticDataRemaining = 0;
                        } else {
                            // 国内流量够用。去除domesticDataLimit
                            domesticDataRemaining -= amount;
                        }
                    }

                }

                if (serviceType.equals(ServiceType.LOCAL_DATA)) {
                    return new ServiceBill(localDataTotal, localDataExtra, localDataExtra * basicCost, localDataLimit);
                } else {
                    return new ServiceBill(domesticDataTotal, domesticDataExtra, domesticDataExtra * basicCost, domesticDataLimit);
                }


            }
        }

    }

    /**
     * 插入Usage
     *
     * @param usage Usage
     * @return 本Usage所花费的话费。
     */
    public double addUsage(Usage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());

            List<UserPlan> activePlans = mapper.getActivePlans(usage.getUserId(), usage.getStartTime());

            ServiceBill serviceBill1 = getBillUsageByServiceType(usage.getServiceType(), usage.getUserId(), usage.getStartTime(), activePlans, basicCost.getCostByServiceType(usage.getServiceType()));

            mapper.addUsage(usage.getUserId(), usage.getStartTime(), usage.getAmount(), usage.getServiceType());

            ServiceBill serviceBill2 = getBillUsageByServiceType(usage.getServiceType(), usage.getUserId(), usage.getStartTime().plusSeconds(1), activePlans, basicCost.getCostByServiceType(usage.getServiceType()));

            return serviceBill2.getCharge() - serviceBill1.getCharge();
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
     * 以指定的时间订阅套餐
     *
     * @param userId              用户ID
     * @param planId              套餐ID
     * @param datetime 订阅时间
     * @param activateImmediately 立即生效？若为false，则为下月生效
     * @return 交易ID
     */
    public int orderPlan(int userId, int planId, LocalDateTime datetime, boolean activateImmediately) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.orderPlan(userId, planId, datetime, activateImmediately);
        }
    }

    /**
     * 以现在的时间订阅套餐。
     * @param userId 用户ID
     * @param planId 套餐ID
     * @param activateImmediately 立即生效？若为false，则为下月生效
     * @return 交易ID
     */

    public int orderPlan(int userId, int planId, boolean activateImmediately) {
        return orderPlan(userId, planId, LocalDateTime.now(), activateImmediately);
    }


    /**
     * 以指定时间取消套餐
     *
     * @param transactionId       订阅此套餐的交易ID
     * @param datetime 取消套餐时间
     * @param activateImmediately 立即生效？若为false，则为下月生效
     */
    public void cancelPlan(int transactionId, LocalDateTime datetime, boolean activateImmediately) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            mapper.cancelPlan(transactionId, datetime, activateImmediately);
        }
    }


    /**
     * 以当前时间取消套餐。
     * @param transactionId       订阅此套餐的交易ID
     * @param activateImmediately 立即生效？若为false，则为下月生效
     */
    public void cancelPlan(int transactionId, boolean activateImmediately) {
        cancelPlan(transactionId, LocalDateTime.now(), activateImmediately);
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
            bill.setPhoneNumber(user.getPhoneNumber());

            // set basic cost

            BasicCost basicCost = mapper.getBasicCost(user.getBasicCostId());

            bill.setBasicCost(basicCost);

            // set active plans

            List<UserPlan> activePlans = mapper.getActivePlans(userId, endDate);

            bill.setActivePlanList(activePlans);

            // 设置各个服务使用量

            ServiceBill callBill = getBillUsageByServiceType(ServiceType.CALL, userId, endDate, activePlans, basicCost.getCallCost());
            bill.setCallBill(callBill);

            ServiceBill smsBill = getBillUsageByServiceType(ServiceType.SMS, userId, endDate, activePlans, basicCost.getSmsCost());
            bill.setSmsBill(smsBill);

            ServiceBill localDataBill = getBillUsageByServiceType(ServiceType.LOCAL_DATA, userId, endDate, activePlans, basicCost.getLocalDataCost());
            bill.setLocalDataBill(localDataBill);

            ServiceBill domesticDataBill = getBillUsageByServiceType(ServiceType.DOMESTIC_DATA, userId, endDate, activePlans, basicCost.getDomesticDataCost());
            bill.setDomesticDataBill(domesticDataBill);



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
