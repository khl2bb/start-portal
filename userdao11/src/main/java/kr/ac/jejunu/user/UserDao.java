package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Object[] params = new Object[]{id};
        String sql = "select id, name, password from userinfo where id = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            User user = null;
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        });
    }

    public void insert(User user) throws SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        String sql = "insert into userinfo (name, password) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) throws SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        Object[] params = new Object[]{id};
        String sql = "delete from userinfo where id = ?";
        jdbcTemplate.update(sql, params);
    }

}

// 이 패턴들이 템플릿 콜백 패턴이구요, 이 안에 있는 구현 메소드는 콜백 메소드라고 이름 붙이게 되구요. 흔히 자바스크립트에서 이벤트처리할때 이런 패턴을 쉽게 찾아볼수있습니다.
// 그리고 필요 없는 클래스들은 빨간불들은 그냥 지우겠습니다. //자동정렬 Ctrl Alt L //ctrl y 하면 라인삭제

//template callback pattern
//java8부터 람다를 활용하는 방법은 메소드가 1개여서 람다를 쓸수있음 지금은
// 메소드의 파라미터로 람다를 활용하게됨

//결과리턴
// 코드 라인 통째로 위치 옮기는 단축키 Alt Shift 방향키

//아래것과 같은 문장
// 그래서 stratetegy pattern 을 사용하는 많은 곳에서는 주로 그 전략자체가 임플리멘트 되는 전략이 메소드가 하나인 경우가 정말 많다. 그래서 그 하나인 경우를 위해서 이 템플릿 콜백 패턴을 위해서 람다라고 하는
// 노테이션이 있고, 이를 활용하면 굉장히 편히 쓸 수 있다.
//        StatementStrategy statementStrategy = new StatementStrategy() {
//            @Override
//            public PreparedStatement makeStatement(Connection connection) throws SQLException {
//                return null;
//            }
//        }

// ctrl shift B         타입 정의 파일 팝업

// shift + f6 으로 변수명 한번에 바꾸기

//ctrl shift 방향키 문단 옮기기

// 단일 책임의 법칙
// 변하는 것과 변하지 않는 것 구분하고 동일한 코드를 메소드로 추출하고
// 추상화, strategy 패턴


// 리팩토링 과정 스킬을 몸으로 체득하면 실무에서 대박이다 연습하고 노력하세여