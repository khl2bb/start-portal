package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class User {
    private Integer id;
    private String name;
    private String password;
    private UserDao userDao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
