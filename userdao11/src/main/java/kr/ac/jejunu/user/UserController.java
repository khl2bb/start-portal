package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @SneakyThrows
    @RequestMapping("/user")
    public User getUser(@RequestParam("id") Integer id) {
        return userDao.get(id);
    }
}
