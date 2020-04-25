package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource; // shift + f6 으로 변수명 한번에 바꾸기

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try { // ctrl + alt + T 하면 surround with 단축키
            //mysql
            //driver 로딩
            connection = dataSource.getConnection();
            //query
            preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            //실행
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                //결과매핑
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            //자원해지
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
                connection.close(); // 커넥션 클로즈를 안하면 쌓여서 터짐 문제는 exception 던지고 끝나버리니까 close를 못함 그래서 try finally
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //결과리턴
        return user;
    }

    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //mysql
            //driver 로딩
            connection = dataSource.getConnection();
            //query
            preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            //실행
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            //결과매핑
            user.setId(resultSet.getInt(1));
        } finally {
            //자원해지
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
            //mysql
            //driver 로딩
            connection = dataSource.getConnection();
            //query
            preparedStatement = connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3,user.getId());
            preparedStatement.executeUpdate();
        } finally {
            //자원해지
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
            //mysql
            //driver 로딩
            connection = dataSource.getConnection();
            //query
            preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            //자원해지
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
