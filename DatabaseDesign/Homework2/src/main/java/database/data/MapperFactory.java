package database.data;

import database.Main;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.function.Consumer;

public class MapperFactory {

    private static SqlSessionFactory factory;

    static {
        org.apache.ibatis.logging.LogFactory.useStdOutLogging();

        String resource = "/mybatis-config.xml";
        InputStream inputStream = Main.class.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSession() {
        return factory.openSession(true);
    }

    public static <T> void processMapper(Class<T> clazz, Consumer<T> consumer) {
        try (SqlSession session = getSession()) {
            T mapper = session.getMapper(clazz);
            consumer.accept(mapper);
        }
    }
}