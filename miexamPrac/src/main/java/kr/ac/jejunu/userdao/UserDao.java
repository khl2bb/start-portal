package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new GetStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);


            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));

            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close(); // 또 여기서 exception 뜨면 throw때문에 뒤에문장이 실행안될수있으니 흘려보내기
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close(); // 커넥션 클로즈를 안하면 쌓여서 터짐 문제는 exception 던지고 끝나버리니까 close를 못함
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException { //사용자 추가기능
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new InsertStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new UpdateStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
