package database.data;

import database.model.FreeLimit;
import database.model.Plan;
import database.model.SmsUsage;
import database.model.UsageCharge;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MainDataService {
    public List<Plan> getAllPlans() {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);

            return mapper.getAllPlans();
        }
    }

    public FreeLimit getFreeLimit() {

    }

    public UsageCharge addSmsUsage(SmsUsage usage) {
        try (SqlSession sqlSession = MapperFactory.getSession()) {
            MainMapper mapper = sqlSession.getMapper(MainMapper.class);



            mapper.addSmsUsage(usage.getUserId(), usage.getTime());


        }
    }
}
