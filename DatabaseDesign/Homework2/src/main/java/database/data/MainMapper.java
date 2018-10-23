package database.data;

import database.model.*;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.util.List;

public interface MainMapper {

    /**
     * 获得所有可用套餐
     * @return 所有可用套餐
     */
    List<Plan> getAllPlans();

    /**
     * 获得用户正在使用的套餐
     * @param userId 用户ID
     * @return 正在使用的套餐
     */
    List<Plan> getActivePlans(@Param("userId") int userId);

    /**
     * 订阅套餐
     * @param userId 用户ID
     * @param planId 套餐ID
     * @param activateImmediately 立即生效？若为false，则为下月生效
     * @return 交易ID
     */
    int orderPlan(@Param("userId") int userId,
                   @Param("planId") int planId,
                   @Param("activateImmediately") boolean activateImmediately
    );

    /**
     * 取消套餐
     * @param transactionId 订阅此套餐的交易ID
     * @param activateImmediately 立即生效？若为false，则为下月生效
     */

    void cancelPlan(@Param("transactionId") int transactionId,
                    @Param("activateImmediately") boolean activateImmediately
    );

    void addCallUsage(@Param("userId") int userId,
                      @Param("startTime") Instant startTime,
                      @Param("endTime") Instant endTime,
                      @Param("duration") double duration
    );

    void addDataUsage(@Param("userId") int userId,
                      @Param("startTime") Instant startTime,
                      @Param("endTime") Instant endTime,
                      @Param("amount") double amount,
                      @Param("dataType") DataUsageType dataType
    );

    void addSmsUsage(@Param("userId") int userId,
                     @Param("time") Instant time
    );

    Usage getCallUsage(@Param("userId") int userId);

    Usage getSmsUsage(@Param("userId") int userId);

    Usage getLocalDataUsage(@Param("userId") int userId);

    Usage getDomesticDataUsage(@Param("userId") int userId);

    BasicCost getBasicCost(@Param("userId") int userId);

    User getUser(@Param("userId") int userId);
}