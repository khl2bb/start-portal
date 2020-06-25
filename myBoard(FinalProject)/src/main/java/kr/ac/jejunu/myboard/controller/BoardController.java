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
    public String home(Model model) { // index, 처음 페이지
        List<MemoDto> memoList = memoService.getMemolist(1);

        model.addAttribute("memoList", memoList);
        return "home.html"; // 리턴 값으로 home.html templates 상대 경로
    }

    @PostMapping("/post")
    public String save(MemoDto memoDto) { // .savePost() 사용해서, 저장하기
        memoService.savePost(memoDto);

        return "redirect:/"; // 리턴 값으로 리다이렉트
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) { // 전체 메모 불러와서 리스트로 보여주기
        List<MemoDto> memoList = memoService.getMemolist(pageNum);
        Integer[] pageList = memoService.getPageList(pageNum); // page 이름이 넘어오면 파라미터 받기, 기본 값 1, 페이지 번호 넘기기, getPageList

        model.addAttribute("memoList", memoList);
        model.addAttribute("pageList", pageList);
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

    @PostMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) { // delete 맵핑,
        memoService.deletePost(no);

        return "redirect:/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) { //keyword를 받는 search()
        List<MemoDto> memoDtoList = memoService.searchPosts(keyword); // keyword로 검색

        model.addAttribute("memoList", memoDtoList);

        return "/memoList.html";
    }

    @GetMapping("/tagsearch")
    public String tagsearch(@RequestParam(value="tag") String tag, Model model) { // tag를 받는 search()
        List<MemoDto> memoDtoList = memoService.searchTagPosts(tag); // tag 검색

        model.addAttribute("memoList", memoDtoList);

        return "/memoList.html";
    }

}
