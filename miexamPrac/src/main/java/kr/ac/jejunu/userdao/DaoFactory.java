package kr.ac.jejunu.userdao;

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

@Configuration //Bean 을정의하는곳
public class DaoFactory {

    @Value("${db.classname}")
    private String className;
    @Value(("${db.url}"))
    private String url;
    @Value(("${db.username}"))
    private String username;
    @Value(("${db.password}"))
    private String password;

    @Bean // new를 해줘서 디펜덴시를 담아서 인스턴스를 반환하는것, 스프링이 해주는것
    public UserDao userDao() {
        return new UserDao(jdbcContext());
    }

    public JdbcTemplate jdbcContext() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() { // connection과 관련된 여러가지 interface를 제공하는 것
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
