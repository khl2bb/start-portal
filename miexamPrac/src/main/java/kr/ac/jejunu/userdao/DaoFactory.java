package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Bean 을정의하는곳
public class DaoFactory {

    @Bean // new를 해줘서 디펜덴시를 담아서 인스턴스를 반환하는것, 스프링이 해주는것
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean // 스프링이 new 해주는 오브젝트 instance를 bean이라 한다.
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

}
