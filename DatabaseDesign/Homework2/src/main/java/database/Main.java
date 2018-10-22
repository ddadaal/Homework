package database;

import database.mapper.CreatorMapper;
import database.mapper.MainMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String resource = "/mybatis-config.xml";
        InputStream inputStream = Main.class.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CreatorMapper creatorMapper = sqlSession.getMapper(CreatorMapper.class);
        creatorMapper.reset();

//        creatorMapper.insertAPlan();

        sqlSession.commit();

//
//        MainMapper mapper = sqlSession.getMapper(MainMapper.class);
//        System.out.println(mapper.getAllPlans());
//
//        mapper.orderPlan(1, 1, true);
//        mapper.orderPlan(1,1,false);

        sqlSession.commit();



    }
}
