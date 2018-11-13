package wang.ismy.oa.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.dao.MessageDao;
import wang.ismy.oa.entity.Message;
import wang.ismy.oa.service.CommunicationService;
import wang.ismy.oa.service.MessageService;

import java.util.LinkedList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    /*消息队列*/
    private List<Message> messageQueue=new LinkedList<>();

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private CommunicationService communicationService;

    @Override
    public void sendMessage(Message message) {
        messageQueue.add(message);

        if(saveMessage(message) != 1){
            logger.error("保存消息失败："+message);
        }


        pushMessageToUser(message);
        System.err.println("当前消息队列：");

        System.err.println(messageQueue);

    }

    private int saveMessage(Message message){

        return messageDao.saveMessage(message);
    }

    @Override
    public List<Message> pullCurrentUserAllMessage() {
        return null;
    }

    /*
    *
    * 如果接收方不在线，则保存到队列，否则从队列删除，并且通过webSocket推送给用户
    * */
    @Override
    public void pushMessageToUser(Message message) {
        if(communicationService.online(message.getMessageTo().getUserId())){

            messageQueue.remove(message);
            communicationService.pushMessage(message);
        }else{
            logger.error("接收方不在线，存放到队列");
        }

    }
}
