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

    @GetMapping("/")
    public String home() { // index, 처음 페이지

        return "home.html"; // 리턴 값으로 home.html templates 상대 경로
    }

    @PostMapping("/post")
    public String save(MemoDto memoDto) { // .savePost() 사용해서, 저장하기
        memoService.savePost(memoDto);

        return "redirect:/"; // 리턴 값으로 리다이렉트
    }

    @GetMapping("/list")
    public String list(Model model) { // 전체 메모 불러와서 리스트로 보여주기
        List<MemoDto> memoList = memoService.getMemolist();

        model.addAttribute("memoList", memoList);
        return "memolist.html";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) { // detail.html 맵핑, 메모 하나씩 보기
        MemoDto memoDTO = memoService.getPost(no);

        model.addAttribute("memoDto", memoDTO);
        return "detail.html";
    }


    @GetMapping("/post/edit/{no}") // Long no 아이디 키 받기
    public String edit(@PathVariable("no") Long no, Model model) { // 메모 수정하기 edit
//        PathVariable은 변수 받기
        MemoDto memoDTO = memoService.getPost(no);

        model.addAttribute("memoDto", memoDTO);
        return "edit.html";
    }

    @PostMapping("/post/edit/{no}")
    public String update(MemoDto memoDTO) { // Put 사용, update 쿼리, savePost 사용
        memoService.savePost(memoDTO);

        return "redirect:/list";
    }

}
