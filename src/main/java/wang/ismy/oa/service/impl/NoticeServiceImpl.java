package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.dao.NoticeDao;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.service.NoticeService;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;
    @Override
    public List<Notice> getNoticeListByLeader(User leader) {
//        如果此用户没有上级
        if(leader==null){
            return noticeDao.getPublicNoticeList();
        }

        return noticeDao.getNoticeListByLeaderId(leader.getUserId());
    }

    @Override
    public List<Notice> getNoticeListByUserId(Integer userId, Page page) {

        return noticeDao.getNoticeListByUserIdByPage(userId,page);
    }


}
