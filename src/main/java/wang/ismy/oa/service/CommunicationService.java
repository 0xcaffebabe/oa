package wang.ismy.oa.service;

import org.springframework.web.socket.WebSocketSession;
import wang.ismy.oa.dto.MessageDto;
import wang.ismy.oa.entity.Message;

public interface CommunicationService {

    void addUser(WebSocketSession session,String sessionId);

    void removeUser(WebSocketSession session);

    void sendMessage(MessageDto messageDto,WebSocketSession session);

    /*判断某个用户是否在线*/
    boolean online(Integer userId);

    /*推送消息*/
    void pushMessage(Message message);
}
