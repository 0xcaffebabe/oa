package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.service.CommunicationService;

@RestController
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    private CommunicationService communicationService;

    @GetMapping("/onlineUser")
    @LoginOnly
    public Object getOnlineUserList(){
        return new Result<>(ResultState.SUCCESS,communicationService.getOnlineUserList());
    }
}
