package wang.ismy.oa.dao;

import org.apache.ibatis.annotations.Param;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Event;

import java.util.List;

public interface EventDao {

    List<Event> getEventListByUserId(Integer userId);

    int saveEvent(Event event);

    Event getEventById(Integer eventId);

    int deleteEventById(Integer eventId);

    List<Event> getEventListByPage(@Param("page") Page page);

    int updateEvent(Event event);
}
