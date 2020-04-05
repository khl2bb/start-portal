package kr.ac.jejunu.usermanagement;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // final 객체 생명력 부여
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/List")
    public List<User> list() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
