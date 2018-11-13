package wang.ismy.oa.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.entity.Message;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ActiveMqServiceTest extends BaseTest {



    /*
    * admin
    * */
    @Test
    public void test(){
        Message message=new Message();
        message.setMessageContent("假装这个是消息");

    }
}