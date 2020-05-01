package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

// bean을 정의하는 곳
@Configuration
public class DaoFactory {
    // data dependency 를 환경 변수로 받는 걸로 제거
    @Value("${db.classname}")
    private String className;
    @Value(("${db.url}"))
    private String url;
    @Value(("${db.username}"))
    private String username;
    @Value(("${db.password}"))
    private String password;

    // 오브젝트 디펜덴시를 받아서 new를 해주는 것
    // 스프링 bean은 스프링이 new 해주는 오브젝트 인스턴스를 bean이라 한다.
    @Bean
    public UserDao userDao() { // ctrl + B 하면 usage 문서로 바로 에디터 이동 ctrl + 좌클릭 으로도 가능

        return new UserDao(jdbcContext());
    }

    @Bean
    public JdbcTemplate jdbcContext() {
        return new JdbcTemplate(dataSource());
        // 이녀석이 data dependency를 가지게
        // 이렇게 의존성 관계가 정립이 되면 지금 userdao는
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
