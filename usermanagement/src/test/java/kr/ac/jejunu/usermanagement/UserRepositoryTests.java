package kr.ac.jejunu.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll() {
//        Integer id = 1;
        String name = "이진우";
        User user = User.builder().name(name).build();
        entityManager.persist(user);
        List<User> users = userRepository.findAll();
        assertThat(users.get(0).getId(), greaterThan(0));
        assertThat(users.get(0).getName(), is(name));
    }
}
