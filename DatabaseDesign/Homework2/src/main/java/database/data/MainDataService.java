package database.data;

import database.model.*;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
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
     * 增加短信使用。
     * 资费都是实时计算的，因为没有人会细致到看每一条短信/每一通电话/每一次流量使用需要花费多少钱！
     * @param usage 短信使用对象
     * @return 产生的资费
     */

    public double addSmsUsage(SmsUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());


            mapper.addSmsUsage(usage.getUserId(), usage.getTime());

            double extra = mapper.getSmsUsage(usage.getUserId()).getExtra();

            if (extra < 0){
                return basicCost.getSmsCost() * 1;
            } else {
                return 0;
            }


        }
    }

    /**
     * 增加打电话使用
     * 资费都是实时计算的，因为没有人会细致到看每一条短信/每一通电话/每一次流量使用需要花费多少钱！
     * @param usage 电话使用对象
     * @return 产生的资费
     */

    public double addCallUsage(CallUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);



            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());
            double extra = mapper.getCallUsage(usage.getUserId()).getExtra();

            mapper.addCallUsage(usage.getUserId(), usage.getStartTime(), usage.getEndTime(), usage.getDuration());

            if (extra < usage.getDuration()){
                return basicCost.getCallCost() * Math.max(usage.getDuration(), usage.getDuration() - extra);
            } else {
                return  0;
            }
        }
    }

    /**
     * 增加流量使用
     * 资费都是实时计算的，因为没有人会细致到看每一条短信/每一通电话/每一次流量使用需要花费多少钱！
     * @param usage 流量使用
     * @return 产生的资费
     */

    public double addDataUsage(DataUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);



            BasicCost basicCost = mapper.getBasicCost(usage.getUserId());

            if (usage.getDataType().equals(DataUsageType.LOCAL)) {
                double extra = mapper.getLocalDataUsage(usage.getUserId()).getExtra();

                mapper.addDataUsage(usage.getUserId(), usage.getStartTime(), usage.getEndTime(), usage.getAmount(), usage.getDataType());


                if (extra < usage.getAmount()) {
                    return basicCost.getLocalDataCost() * Math.max(usage.getAmount(), usage.getAmount() - extra);
                } else {
                    return 0;
                }
            } else {
                double extra = mapper.getLocalDataUsage(usage.getUserId()).getExtra();


                mapper.addDataUsage(usage.getUserId(), usage.getStartTime(), usage.getEndTime(), usage.getAmount(), usage.getDataType());


                if (extra < usage.getAmount()) {
                    return basicCost.getDomesticDataCost() * Math.max(usage.getAmount(), usage.getAmount() - extra);
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
     * 获得指定月份的账单
     * @param userId 用户ID
     * @param endDate 日期。只会使用date的年和月部分。
     * @return 账单
     */
    public Bill generateBill(int userId, LocalDate endDate) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            Bill bill = new Bill();

            // set user information
            User user = mapper.getUser(userId);


            bill.setUserId(userId);
            bill.setName(user.getName());

            // set basic cost

            BasicCost basicCost = mapper.getBasicCost(user.getBasicCost());

            bill.setBasicCost(basicCost);

            // set active plans

            List<Plan> activePlans = mapper.getActivePlans(userId);

            bill.setActivePlanList(activePlans);

            // set date range for this query
            YearMonth month = YearMonth.from(endDate);

            YearMonth today = YearMonth.now();

            LocalDate startDate = month.atDay(1);

            if (month.isBefore(today)) {
                // queried month is not this month
                // end the end date to the last day of that month
                endDate = month.atEndOfMonth();
            }
            bill.setStartDate(startDate);

            bill.setEndDate(endDate);

            // set usages

            Usage callUsage = mapper.getCallUsage(userId);

            Usage smsUsage = mapper.getSmsUsage(userId);

            Usage localDataUsage = mapper.getLocalDataUsage(userId);

            Usage domesticDataUsage = mapper.getDomesticDataUsage(userId);


            bill.setTotalCallUsage((int)callUsage.getTotal());
            bill.setExtraCallUsage((int)callUsage.getExtra());
            bill.setExtraCallFee(basicCost.getCallCost() * callUsage.getExtra());

            bill.setTotalSmsUsage((int)smsUsage.getTotal());
            bill.setExtraSmsUsage((int)smsUsage.getExtra());
            bill.setExtraSmsFee(basicCost.getSmsCost() * smsUsage.getExtra());

            bill.setTotalLocalDataUsage(localDataUsage.getTotal());
            bill.setExtraLocalDataUsage(localDataUsage.getExtra());
            bill.setExtraLocalDataFee(basicCost.getLocalDataCost() * localDataUsage.getExtra());

            bill.setTotalDomesticDataUsage(domesticDataUsage.getTotal());
            bill.setExtraDomesticDataUsage(domesticDataUsage.getExtra());
            bill.setExtraDomesticFee(basicCost.getDomesticDataCost() * domesticDataUsage.getExtra());


            return bill;


        }
    }
}
