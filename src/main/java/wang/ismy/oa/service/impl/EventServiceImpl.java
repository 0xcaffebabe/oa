package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dao.EventDao;
import wang.ismy.oa.dto.EventDto;
import wang.ismy.oa.entity.Event;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.exception.EventException;
import wang.ismy.oa.exception.EventTimeException;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.exception.PermissionDeniedException;
import wang.ismy.oa.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;
    @Override
    public List<Event> getEventListByUserId(Integer userId) {

        if(userId==null){
            throw new IllegalArgumentException("userId not null!");
        }


        return eventDao.getEventListByUserId(userId);
    }

    @Override
    public int saveEvent(EventDto eventDto, User user) throws NotLoginException {
        if(user==null){
            throw new NotLoginException(DataDictionary.NOT_LOGIN.toString());
        }

        //开始时间大于结束时间
        if(eventDto.getEventStart().compareTo(eventDto.getEventEnd()) > 0){
            throw new EventTimeException(DataDictionary.START_OVER_END.toString());
        }

        //某个时间小于当前时间
        LocalDateTime now=LocalDateTime.now();
        if(eventDto.getEventStart().compareTo(now)<0
                ||eventDto.getEventEnd().compareTo(now)<0){
            throw new EventTimeException(DataDictionary.TIME_OVER.toString());
        }

        //如果是全天事件
        if(eventDto.getEventAllDay()){
            eventDto.setEventStart(eventDto.getEventStart().withHour(0).withMinute(0).withSecond(0));
            eventDto.setEventEnd(eventDto.getEventStart().withHour(23).withMinute(59).withSecond(59));

        }else{
            //非全天事件但是两个时间相等
            if(eventDto.getEventStart().compareTo(eventDto.getEventEnd())==0){
                throw new EventTimeException(DataDictionary.TIME_EQUALS.toString());
            }
        }

        Event event=new Event();
        event.setEventUser(user);
        event.setEventName(eventDto.getEventName());
        event.setEventDesc(eventDto.getEventDesc());
        event.setEventStartTime(eventDto.getEventStart());
        event.setEventEndTime(eventDto.getEventEnd());
        return eventDao.saveEvent(event);
    }

    @Override
    public int deleteEventById(Integer eventId, User user) {
        Event event=getEventById(eventId,user);


        return eventDao.deleteEventById(eventId);
    }

    @Override
    public Event getEventById(Integer eventId, User user) {
        Event event = eventDao.getEventById(eventId);

        if(event == null){
            throw new EventException(DataDictionary.EVENT_NOT_EXIST.toString());
        }

        if(!event.getEventUser().getUserId().equals(user.getUserId())){
            throw new PermissionDeniedException(DataDictionary.PERMISSION_DENIED.toString());
        }

        return event;
    }


}
