package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dto.ImgFileDto;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.service.UserInfoService;
import wang.ismy.oa.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    @LoginOnly
    public Object getUserInfo(@SessionAttribute(value = "user",required = false)User user){
        return new Result<>(ResultState.SUCCESS,userInfoService.getUserInfo(user.getUserId()));
    }

    /*
    * 获取某个下级的用户信息
    */
    @GetMapping("/staff/{staffId}")
    @LoginOnly
    public Object getStaffUserInfo(@PathVariable("staffId") Integer staffId){

        return new Result<>(ResultState.SUCCESS,userInfoService.getStaffUserInfo(staffId));
    }

    /*
    * 修改当前登录用户头像
    */
    @PostMapping("/profile")
    @LoginOnly
    public Object updateUserProfile(@RequestParam("file") MultipartFile file) throws IOException {
        ImgFileDto dto = new ImgFileDto();

        String extension = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length());


        dto.setFileName(System.currentTimeMillis()+"."+extension);

        dto.setInputStream(file.getInputStream());

        User currentUser = userService.getCurrentUser();

        if(userInfoService.updateUserProfile(currentUser,dto)==1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.UPDATE_SUCCESS.toString());
        }else{
            return new Result<>(ResultState.ERROR,DataDictionary.UPDATE_FAIL.toString());
        }



    }
}
