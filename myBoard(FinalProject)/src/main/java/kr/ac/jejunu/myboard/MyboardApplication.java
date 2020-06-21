package kr.ac.jejunu.myboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Jpa Auditing 활성화를 위해
@SpringBootApplication
public class MyboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyboardApplication.class, args);
    }

}
