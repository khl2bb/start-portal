package kr.ac.jejunu.myboard.controller;

import kr.ac.jejunu.myboard.dto.MemoDto;
import kr.ac.jejunu.myboard.service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 컨트롤러 어노테이션
@AllArgsConstructor // Bean 주입 방식, 생성자로 Bean 객체 받기
public class BoardController {
    private MemoService memoService;

    @RequestMapping("/")
    public String home() { // index, 처음 페이지

        return "home.html"; // 리턴 값으로 home.html templates 상대 경로
    }

    @PostMapping("/post")
    public String save(MemoDto memoDto) {
        memoService.savePost(memoDto);

        return "redirect:/"; // 리턴 값으로 리다이렉트
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<MemoDto> memoList = memoService.getMemolist();

        model.addAttribute("memoList", memoList);
        return "memolist.html";
    }


}
