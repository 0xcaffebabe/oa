package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.oa.valid.CustomValid;
import wang.ismy.oa.dto.LoginDto;
import wang.ismy.oa.service.LoginService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public Object login(@RequestBody @CustomValid LoginDto loginDto, HttpSession session){
        return loginService.login(loginDto,session);
    }

    @GetMapping("")
    public Object hasLogin(HttpSession httpSession){
        return loginService.hasLogin(httpSession);
    }

    @DeleteMapping("")
    public Object logout(HttpSession httpSession){
        return loginService.logout(httpSession);
    }



}
