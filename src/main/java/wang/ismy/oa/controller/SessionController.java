package wang.ismy.oa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.enums.ResultState;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/")
    public Object getSessionId(HttpSession session){

        return new Result<>(ResultState.SUCCESS,session.getId());
    }
}
