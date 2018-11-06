package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dao.EventDao;
import wang.ismy.oa.entity.Event;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.EventService;

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
}
