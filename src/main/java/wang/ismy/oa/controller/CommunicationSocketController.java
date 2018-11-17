package wang.ismy.oa.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import wang.ismy.oa.dto.MessageDto;
import wang.ismy.oa.service.CommunicationService;
import wang.ismy.oa.service.MessageService;

@Component
public class CommunicationSocketController extends TextWebSocketHandler {

    @Autowired
    private CommunicationService communicationService;



    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("close....");
        communicationService.removeUser(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("建立新的会话");

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        if(message.getPayload().equals("heart")){

            return;
        }

        if(!message.getPayload().contains("{")){

            communicationService.addUser(session,message.getPayload());

        }else{
            Gson gson=new Gson();

            MessageDto dto=gson.fromJson(message.getPayload(),MessageDto.class);


            communicationService.sendMessage(dto,session);


        }

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }


}
