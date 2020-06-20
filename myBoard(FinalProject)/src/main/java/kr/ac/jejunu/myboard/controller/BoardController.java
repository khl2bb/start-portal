package kr.ac.jejunu.myboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class BoardController {

    @RequestMapping("/")
    public String home() { // index, 처음 페이지

        return "home.html";
    }

}
