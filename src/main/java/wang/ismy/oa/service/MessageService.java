package wang.ismy.oa.service;

import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Message;
import wang.ismy.oa.entity.User;

import java.util.List;

public interface MessageService {

    /*
    * 发送消息接口
    * */
    void sendMessage(Message message);

    /*
    * 拉取当前登录用户所有消息
    */
    List<Message> pullCurrentUserAllMessage();

    /*
    * 向某个用户推送消息
    */
    void pushMessageToUser(Message message);

    List<Message> getCurrentMessageListByFromByPage(Integer fromUser, Page page);




}
