package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    // shift + f6 으로 변수명 한번에 바꾸기
    private final JdbcContext jdbcContext;
// ctrl shift B         타입 정의 파일 팝업
    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = connection -> {
            //query
            PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        }; //아래것과 같은 문장
        // 그래서 stratetegy pattern 을 사용하는 많은 곳에서는 주로 그 전략자체가 임플리멘트 되는 전략이 메소드가 하나인 경우가 정말 많다. 그래서 그 하나인 경우를 위해서 이 템플릿 콜백 패턴을 위해서 람다라고 하는
        // 노테이션이 있고, 이를 활용하면 굉장히 편히 쓸 수 있다.
//        StatementStrategy statementStrategy = new StatementStrategy() {
//            @Override
//            public PreparedStatement makeStatement(Connection connection) throws SQLException {
//                return null;
//            }
//        }
        //template callback pattern
        //java8부터 람다를 활용하는 방법은 메소드가 1개여서 람다를 쓸수있음 지금은
        // 메소드의 파라미터로 람다를 활용하게됨
        //결과리턴
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    // 코드 라인 통째로 위치 옮기는 단축키 Alt Shift 방향키
    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            //query
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            //query
            PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3,user.getId());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        //자동정렬 Ctrl Alt L
        StatementStrategy statementStrategy = connection -> {
            //ctrl y 하면 라인삭제
            //query
            PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    } // 이 패턴들이 템플릿 콜백 패턴이구요, 이 안에 있는 구현 메소드는 콜백 메소드라고 이름 붙이게 되구요. 흔히 자바스크립트에서 이벤트처리할때 이런 패턴을 쉽게 찾아볼수있습니다.
    // 그리고 필요 없는 클래스들은 빨간불들은 그냥 지우겠습니다.
}
