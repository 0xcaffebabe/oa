package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.service.UserInfoService;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("")
    @LoginOnly
    public Object getUserInfo(@SessionAttribute(value = "user",required = false)User user){
        return new Result<>(ResultState.SUCCESS,userInfoService.getUserInfo(user.getUserId()));
    }
}
