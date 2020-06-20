package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @GetMapping("/user/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userDao.findById(id).get();
    }

    @GetMapping("/index")
    public String index() {
        return "<h1>this is index</h1>\n" +
                "\t\t<ul>\n" +
                "\t\t\t<li>\n" +
                "\t\t\t\t<a href=\"/user/1\">1</a>\n" +
                "\t\t\t</li>\n" +
                "\t\t</ul>";
    }

    @RequestMapping("/indexee")
    public void indexee() {

    }
}
