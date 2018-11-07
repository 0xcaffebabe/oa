package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dto.EventDto;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.entity.Event;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.EventService;
import wang.ismy.oa.service.UserService;
import wang.ismy.oa.valid.CustomValid;

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

    @GetMapping("/{eventId}")
    @LoginOnly
    public Object getEventById(@PathVariable("eventId") Integer eventId){
        User user = userService.getCurrentUser();
        return new Result<>(ResultState.SUCCESS,eventService.getEventById(eventId,user));
    }


    @PostMapping("/")
    @LoginOnly
    public Object saveEvent(@RequestBody @CustomValid EventDto eventDto) throws NotLoginException {

        User user=userService.getCurrentUser();

        if(eventService.saveEvent(eventDto,user)==1){
            return new Result<>(ResultState.SUCCESS,"增加事件成功");
        }else{
            return new Result<>(ResultState.ERROR,"增加事件失败");
        }

    }

    /*更新event web接口*/
    @PostMapping("/{eventId}")
    @LoginOnly
    public Object updateEvent(@RequestBody @CustomValid EventDto eventDto,@PathVariable("eventId") Integer eventId){

        if(eventService.updateEvent(eventDto,eventId) == 1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.UPDATE_SUCCESS);
        }

        return new Result<>(ResultState.SUCCESS,DataDictionary.UPDATE_FAIL);
    }

    @DeleteMapping("/{eventId}")
    public Object deleteEventById(@PathVariable("eventId") Integer eventId){

        User user = userService.getCurrentUser();

        if(eventService.deleteEventById(eventId,user) == 1){
            return new Result<>(ResultState.SUCCESS,DataDictionary.DELETE_SUCCESS);
        }else{
            return new Result<>(ResultState.SUCCESS,DataDictionary.DELETE_FAIL);
        }
    }

}
