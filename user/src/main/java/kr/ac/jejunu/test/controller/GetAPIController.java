package kr.ac.jejunu.test.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // 여기는 컨트롤러라고 알려주는 @RestController 어노테이션 사용
@RequestMapping("/api") // 여기로 들어올 path를 지정할 @RequestMapping 어노테이션 사용 localhost:8080/api
public class GetAPIController {

    @RequestMapping(method = RequestMethod.GET, path = "/getRequest")   // localhost:8080/api/getRequest
    public String getRequest(){
        return "this is getRequest";
    }

}