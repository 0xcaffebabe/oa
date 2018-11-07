package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.NoticeService;
import wang.ismy.oa.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @GetMapping("/self")
    @LoginOnly
    /*
    * 获取当前登录用户应该看到的通知:上级发布的通知、面向全公司的通知
    * */
    public Object getSelfNotice() throws NotLoginException {
        User user=userService.getCurrentUser();

        return new Result<>(ResultState.SUCCESS,noticeService.getNoticeListByLeader(user.getUserInfo().getLeader()));
    }

    /*获取当前用户发布的所有公告*/
    @GetMapping("/list")
    @LoginOnly
    public Object getOwnNotice(@RequestParam("page") Integer pageNumber,@RequestParam("length") Integer length){
        Page page = new Page(pageNumber,length);

        User user = userService.getCurrentUser();

        return new Result<>(ResultState.SUCCESS,noticeService.getNoticeListByUserId(user.getUserId(),page));
    }
}
