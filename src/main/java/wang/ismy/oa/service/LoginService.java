package wang.ismy.oa.service;

import wang.ismy.oa.dto.LoginDto;
import wang.ismy.oa.dto.Result;

import javax.servlet.http.HttpSession;

public interface LoginService {

    Result<String> login(LoginDto loginDto, HttpSession session);

    Result<Boolean> hasLogin(HttpSession session);

    Result<String> logout(HttpSession httpSession);
}
