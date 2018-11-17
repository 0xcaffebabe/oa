package wang.ismy.oa.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.dto.Page;

import static org.junit.Assert.*;

public class MessageDaoTest extends BaseTest {

    @Autowired
    private MessageDao messageDao;

    @Test
    public void getMessageListByPage() {

        assertEquals(10,messageDao.getMessageListByPage(2,2,new Page(1,10)).size());
    }
}