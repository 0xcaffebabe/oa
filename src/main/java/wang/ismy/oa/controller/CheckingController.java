package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dto.CheckingDto;
import wang.ismy.oa.dto.DutyStateDTO;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.CheckingEnum;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.CheckingService;
import wang.ismy.oa.service.UserService;
import wang.ismy.oa.valid.CustomValid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/checking")
public class CheckingController {


    @Autowired
    private CheckingService checkingService;

    @Autowired
    private UserService userService;
    /*
    * 打卡接口
    * */
    @PostMapping("/")
    @LoginOnly
    public Object checking() throws NotLoginException {

        int ret=checkingService.addCheckingRecord();
        if(ret==1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.CHECKING_SUCCESS.toString());
        }


        return new Result<>(ResultState.SUCCESS,DataDictionary.CHECKING_FAIL.toString());
    }

    /*
    * 获取打卡时间接口
    * */
    @GetMapping("/time")
    @LoginOnly
    public Object getCheckingTime(){

        return new Result<>(ResultState.SUCCESS,checkingService.getCheckingTime());
    }

    /*
    * 是否已打卡上班（已废弃）
    * */
    @GetMapping("/hasOnDuty")
    @LoginOnly
    @Deprecated
    public Object hasOnDuty() throws NotLoginException {
       return new Result<>(ResultState.SUCCESS,checkingService.hasDutyState(CheckingEnum.ON_DUTY));
    }


    /*
    * 是否已打卡下班(已废弃)
    * */
    @GetMapping("/hasOffDuty")
    @LoginOnly
    @Deprecated
    public Object hasOffDuty() throws NotLoginException {
        return new Result<>(ResultState.SUCCESS,checkingService.hasDutyState(CheckingEnum.OFF_DUTY));
    }


    /*
    * 获取上下班打卡状态
    * */
    @GetMapping("/dutyState")
    @LoginOnly
    public Object getDutyState() throws NotLoginException {
        DutyStateDTO dutyStateDTO=new DutyStateDTO();
        dutyStateDTO.setOffDuty(checkingService.hasDutyState(CheckingEnum.OFF_DUTY));
        dutyStateDTO.setOnDuty(checkingService.hasDutyState(CheckingEnum.ON_DUTY));
        return new Result<>(ResultState.SUCCESS,dutyStateDTO);
    }

    @GetMapping("/currentTime")
    public Object getCurrentTime(){
        return new Result<>(ResultState.SUCCESS,checkingService.getCurrentDate());
    }

    @GetMapping("/self/recent")
    @LoginOnly
    public Object getSelfRecentCheckingList() throws NotLoginException {

        return new Result<>(ResultState.SUCCESS,checkingService.getSelfRecentCheckingList());
    }

    /*
    * 获取当前登录用户考勤信息
    */
    @GetMapping("/info")
    @LoginOnly
    public Object getCheckingInfoBySelf(){
        User user = userService.getCurrentUser();

        return new Result<>(ResultState.SUCCESS,checkingService.getCheckingInfoByUserId(user.getUserId()));
    }

    /*
    * 获取当前登录用户考勤记录
    */
    @GetMapping("/list")
    @LoginOnly
    public Object getCheckingListBySelf(@RequestParam("page") Integer pageNumber,@RequestParam("length") Integer length){
        Page page=new Page(pageNumber,length);

        User user = userService.getCurrentUser();

        return new Result<>(ResultState.SUCCESS,checkingService.getCheckingListByUserIdByPage(user.getUserId(),page));


    }

    /*
    * 获取当前登录用户的某个下级考勤记录
    */
    @GetMapping("/list/{staffId}")
    @LoginOnly
    public Object getStaffCheckingListByPage(@PathVariable("staffId") Integer staffId,@RequestParam("page") Integer pageNumber,
                                             @RequestParam("length") Integer length){
        Page page = new Page(pageNumber,length);

        return new Result<>(ResultState.SUCCESS,checkingService.getStaffCheckingListByPage(staffId,page));
    }

    @GetMapping("/list/export/{staffId}")
    @LoginOnly
    public Object exportStaffCheckingListByPage(@PathVariable("staffId") Integer staffId,@RequestParam("page") Integer pageNumber,
                                             @RequestParam("length") Integer length){
        Page page = new Page(pageNumber,length);

        return checkingService.exportStaffCheckingListByPage(staffId,page);
    }

}
