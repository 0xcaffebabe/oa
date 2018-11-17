package wang.ismy.oa.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dto.MessageDto;
import wang.ismy.oa.entity.Message;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.exception.UserNotFoundException;
import wang.ismy.oa.listener.SessionListener;
import wang.ismy.oa.service.CommunicationService;
import wang.ismy.oa.service.MessageService;
import wang.ismy.oa.service.UserService;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommunicationServiceImpl implements CommunicationService {

    /*在线用户映射表*/
    private Map<WebSocketSession,Integer> onlineUserMapping = new ConcurrentHashMap<>();

    /*反向在线用户映射表*/
    private Map<Integer,WebSocketSession> reverseOnlineUserMapping=new ConcurrentHashMap<>();

    /*ID与用户信息映射表*/
    private Map<Integer,User> userIdMapping = new ConcurrentHashMap<>();

    @Override
    public boolean online(Integer userId) {
        return reverseOnlineUserMapping.containsKey(userId);
    }



    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SessionListener sessionListener;

    @Override
    public void addUser(WebSocketSession session,String sessionId) {
        HttpSession httpSession=sessionListener.getSessionBySessionId(sessionId);

        User user=null;
        if(httpSession != null){
            user=(User) httpSession.getAttribute("user");


            if(user==null){

                throw new NotLoginException("未登录");
            }
            onlineUserMapping.put(session,user.getUserId());

            userIdMapping.put(user.getUserId(),user);

            reverseOnlineUserMapping.put(user.getUserId(),session);


        }else{


        }



    }

    @Override
    public void removeUser(WebSocketSession session) {

        Integer userId=onlineUserMapping.remove(session);

        if(userId != null){
            reverseOnlineUserMapping.remove(userId);
        }


    }

    @Override
    public void sendMessage(MessageDto messageDto,WebSocketSession session) {

        User user =new User();

        user=userIdMapping.get(onlineUserMapping.get(session));

        User toUser=userService.getUser(messageDto.getMessageTo());

        if(toUser == null){
            System.err.println("to user is null");
            throw new UserNotFoundException(DataDictionary.USER_NOT_EXIST.toString());
        }

        Message message=new Message();
        message.setMessageFrom(user);
        message.setMessageTo(toUser);
        message.setMessageContent(messageDto.getMessageContent());
        message.setHasRead(false);

        messageService.sendMessage(message);

    }

    @Override
    public List<User> getOnlineUserList() {
        User user = userService.getCurrentUser();

        List<User> users=new ArrayList<>();
        for(Integer key :reverseOnlineUserMapping.keySet()){
            users.add(userIdMapping.get(key));
        }
        return users;
    }

    @Override
    public void pushMessage(Message message) {
        Gson gson=new Gson();
        TextMessage textMessage=new TextMessage(gson.toJson(message));

        try {
            reverseOnlineUserMapping.get(message.getMessageTo().getUserId()).sendMessage(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
