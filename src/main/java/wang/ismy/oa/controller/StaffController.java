package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.service.UserService;

@RestController
@RequestMapping("/staff")
public class StaffController {


    @Autowired
    private UserService userService;

    /*
    * 获取当前登录用户所有下级（分页）
    */
    @GetMapping("/")
    @LoginOnly
    public Object getStaffListByPage(@RequestParam("page") Integer pageNumber,@RequestParam("length") Integer length){

        Page page = new Page(pageNumber,length);

        User user = userService.getCurrentUser();

        return new Result<>(ResultState.SUCCESS,userService.getStaffListByPage(user,page));
    }
}
