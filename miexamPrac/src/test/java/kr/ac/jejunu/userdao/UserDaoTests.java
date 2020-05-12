package kr.ac.jejunu.userdao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    @Test
    public void testGetJeju() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "jinoo";
        String password = "1234";

//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testInsertJeju() throws SQLException, ClassNotFoundException {
        String name = "jinooJeju"; // 테스트 할 때 halla jeju 구분해보려고 바꾸었습니다.
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);

        assertThat(user.getId(), greaterThan(0));
        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

//    @Test
//    public void testGetHalla() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        String name = "jinooHalla"; // 테스트 할 때 halla jeju 구분해보려고 바꾸었습니다.
//        String password = "1234";
//
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        User user = userDao.get(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void testInsertHalla() throws SQLException, ClassNotFoundException {
//        String name = "jinooHalla"; // 테스트 할 때 halla jeju 구분해보려고 바꾸었습니다.
//        String password = "1234";
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        userDao.insert(user);
//
//        assertThat(user.getId(), greaterThan(0));
//        User insertedUser = userDao.get(user.getId());
//        assertThat(insertedUser.getName(), is(name));
//        assertThat(insertedUser.getPassword(), is(password));
//    }

}
