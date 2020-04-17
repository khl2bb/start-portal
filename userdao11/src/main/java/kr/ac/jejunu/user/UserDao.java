package kr.ac.jejunu.user;

import java.sql.*;

public abstract class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Connection connection = getConnection();
        //query
        PreparedStatement preparedStatement =
                connection.prepareStatement("select id, name, password from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        //실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Connection connection = getConnection();
        //query
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userinfo (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
        //실행
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        //결과매핑
        user.setId(resultSet.getInt(1));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    abstract public Connection getConnection() throws ClassNotFoundException, SQLException;
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //connection
//        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=Asia/Seoul"
//                , "jeju", "jejupw");

}
