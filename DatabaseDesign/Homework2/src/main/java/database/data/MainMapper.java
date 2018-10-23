package database.data;

import database.model.DataUsageType;
import database.model.Plan;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MainMapper {

    List<Plan> getAllPlans();

    void orderPlan(@Param("userId") int userId,
                   @Param("planId") int planId,
                   @Param("activateImmediately") boolean activateImmediately
    );

    void cancelPlan(@Param("userId") int userId,
                    @Param("planId") int planId,
                    @Param("activateImmediately") boolean activateImmediately
    );

    void addCallUsage(@Param("userId") int userId,
                      @Param("startDate") Date startDate,
                      @Param("endDate") Date endDate,
                      @Param("quantity") double quantity
    );

    void addDataUsage(@Param("userId") int userId,
                      @Param("startDate") Date startDate,
                      @Param("endDate") Date endDate,
                      @Param("quantity") double quantity,
                      @Param("dataType") DataUsageType dataType
    );

    void addSmsUsage(@Param("userId") int userId,
                     @Param("time") Date time
    );

}