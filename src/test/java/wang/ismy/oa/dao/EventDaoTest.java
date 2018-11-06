package wang.ismy.oa.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;

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
}