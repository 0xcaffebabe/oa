package wang.ismy.oa.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.entity.Event;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EventDaoTest extends BaseTest {

    @Autowired
    private EventDao eventDao;
    @Test
    public void getEventById() {


        assertEquals("中文测试abc",eventDao.getEventById(24).getEventDesc());
    }

    @Test
    public void delete(){
        assertEquals(1,eventDao.deleteEventById(3));

    }

    @Test
    public void updateEvent(){
        Event event=new Event();
        event.setEventId(2);
        event.setEventName("不要写小程序了");
        event.setEventEndTime(LocalDateTime.now());

        assertEquals(1,eventDao.updateEvent(event));
    }
}