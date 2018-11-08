package wang.ismy.oa.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.dto.NoticeDto;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.NoticeService;
import wang.ismy.oa.service.UserService;
import wang.ismy.oa.valid.CustomValid;

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

    @GetMapping("/{noticeId}")
    @LoginOnly
    public Object getNoticeByNoticeId(@PathVariable("noticeId") Integer noticeId){

        return new Result<>(ResultState.SUCCESS,noticeService.getNoticeByNoticeId(noticeId));
    }
    /*当前用户发布公告*/
    @PutMapping("/")
    @LoginOnly
    public Object saveNotice(@RequestBody @CustomValid NoticeDto noticeDto){

        User user = userService.getCurrentUser();

        if(noticeService.saveNotice(noticeDto,user)==1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.SAVE_SUCCESS.toString());
        }else{
            return new Result<>(ResultState.ERROR,DataDictionary.SAVE_FAIL.toString());
        }

    }

    /*
    * 根据ID更新公告内容
    * */
    @PostMapping("/{noticeId}")
    @LoginOnly
    public Object updateNotice(@RequestBody @CustomValid NoticeDto noticeDto,
                               @PathVariable("noticeId") Integer noticeId){

        if(noticeService.updateNotice(noticeDto,noticeId) == 1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.UPDATE_SUCCESS.toString());
        }else{
            return new Result<>(ResultState.ERROR,DataDictionary.UPDATE_FAIL.toString());
        }

    }

    /*根据公告ID删除公告*/
    @DeleteMapping("/{noticeId}")
    @LoginOnly
    public Object deleteNoticeByNoticeId(@PathVariable("noticeId") Integer noticeId){
        User user = userService.getCurrentUser();

        if(noticeService.deleteNoticeByNoticeId(noticeId,user) == 1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.DELETE_SUCCESS.toString());
        }
        return new Result<>(ResultState.ERROR,DataDictionary.DELETE_FAIL.toString());

    }

    /*搜索用户发表的公告*/
    @GetMapping("/search")
    @LoginOnly
    public Object searchNotice(@RequestParam("keyword") String keyword,
                               @RequestParam("page") Integer pageNumber,@RequestParam("length") Integer length){

        Page page = new Page(pageNumber,length);

        User user = userService.getCurrentUser();
        
        return new Result<>(ResultState.SUCCESS,noticeService.searchNoticeListByPage(user,keyword,page));
    }
}
