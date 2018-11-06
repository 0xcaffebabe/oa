package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dto.LoginDto;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.service.LoginService;
import wang.ismy.oa.service.UserService;

import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public Result<String> login(LoginDto loginDto, HttpSession session) {
        User user=userService.getUser(loginDto.getUsername());
        if(user!=null){
            if(user.getPassword().equalsIgnoreCase(loginDto.getPassword())){
                //如果登录成功的话，将用户对象存入session
                session.setAttribute("user",user);
                return new Result<>(ResultState.SUCCESS, DataDictionary.LOGIN_SUCCESS.toString());
            }
        }

        return new Result<>(ResultState.ERROR,DataDictionary.LOGIN_FAIL.toString());
    }

    @Override
    public Result<Boolean> hasLogin(HttpSession session) {
        Object user=session.getAttribute("user");

        if(user==null){
            return new Result<>(ResultState.ERROR,false);
        }

        if(user instanceof User){
            return new Result<>(ResultState.SUCCESS,true);
        }else{
            return new Result<>(ResultState.ERROR,false);
        }

    }

    @Override
    public Result<String> logout(HttpSession httpSession) {
        if(httpSession.getAttribute("user")==null){
            return new Result<>(ResultState.ERROR,"注销失败");
        }

        httpSession.removeAttribute("user");
        return new Result<>(ResultState.SUCCESS,"注销成功");
    }
}
