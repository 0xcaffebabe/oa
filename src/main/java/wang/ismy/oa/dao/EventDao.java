package wang.ismy.oa.dao;

import wang.ismy.oa.entity.Event;

import java.util.List;

public interface EventDao {

    List<Event> getEventListByUserId(Integer userId);

    int saveEvent(Event event);

    Event getEventById(Integer eventId);

    int deleteEventById(Integer eventId);
}
