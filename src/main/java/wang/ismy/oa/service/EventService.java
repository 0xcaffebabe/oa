package wang.ismy.oa.service;

import wang.ismy.oa.dto.EventDto;
import wang.ismy.oa.entity.Event;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.exception.NotLoginException;

import java.util.List;

public interface EventService {

    List<Event> getEventListByUserId(Integer userId);

    int saveEvent(EventDto eventDto, User user);

    Event getEventById(Integer eventId,User user);

    int deleteEventById(Integer eventId,User user);

}
