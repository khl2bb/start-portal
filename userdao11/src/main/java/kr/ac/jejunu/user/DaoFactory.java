package kr.ac.jejunu.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// bean을 정의하는 곳
@Configuration
public class DaoFactory {
    // 오브젝트 디펜덴시를 받아서 new를 해주는 것
    // 스프링 bean은 스프링이 new 해주는 오브젝트 인스턴스를 bean이라 한다.
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
