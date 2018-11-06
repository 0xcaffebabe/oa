package wang.ismy.oa.service;

import wang.ismy.oa.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> getEventListByUserId(Integer userId);
}
