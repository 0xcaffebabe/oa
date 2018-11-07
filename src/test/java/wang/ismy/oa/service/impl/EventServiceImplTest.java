package wang.ismy.oa.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.dto.EventDto;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Event;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.service.EventService;

import java.util.List;

import static org.junit.Assert.*;

public class EventServiceImplTest extends BaseTest {

    @Autowired
    private EventService eventService;

    @Test
    public void saveEvent() {
        User user=new User();
        user.setUserId(2);

        EventDto dto=new EventDto();

    }

    @Test
    public void getEventListByPage(){
        Page page=new Page();
        page.setPageNumber(1);
        page.setLength(10);
        List<Event> list = eventService.getEventListByPage(page);
        assertEquals(10,list.size());
    }
}