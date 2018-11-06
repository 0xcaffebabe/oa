package wang.ismy.oa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.entity.Event;

import java.util.List;

import static org.junit.Assert.*;

public class EventServiceTest extends BaseTest {

    @Autowired
    private EventService eventService;
    @Test
    public void getEventListByUserId() {

        List<Event> list=eventService.getEventListByUserId(1);

        System.err.println(list);
    }
}