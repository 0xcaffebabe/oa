package wang.ismy.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.ismy.oa.annotations.LoginOnly;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{fromUser}")
    @LoginOnly
    public Object getMessageListByFromUser(@PathVariable("fromUser") Integer fromUser,
                                           @RequestParam("page") Integer pageNumber,@RequestParam("length") Integer length){

        Page page = new Page(pageNumber,length);

        return new Result<>(ResultState.SUCCESS,messageService.getCurrentMessageListByFromByPage(fromUser,page));
    }
}
