package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.Event;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.EventService;
import wang.ismy.oa.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    /*获取当前登录用户的事件列表*/
    @GetMapping("/self")
    @LoginOnly
    public Object getSelfEvent() throws NotLoginException {
        List<Event> list=eventService.getEventListByUserId(userService.getCurrentUser().getUserId());
        return new Result<>(ResultState.SUCCESS,list);
    }


}
