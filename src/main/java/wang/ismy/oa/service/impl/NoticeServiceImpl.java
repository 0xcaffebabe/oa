package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dao.NoticeDao;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;
import wang.ismy.oa.dto.NoticeDto;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.exception.PermissionDeniedException;
import wang.ismy.oa.service.NoticeService;
import wang.ismy.oa.service.UserService;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private UserService userService;
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

    @Override
    public int saveNotice(NoticeDto noticeDto, User user) {

        Notice notice = noticeDtoToNotice(noticeDto,user);

        return noticeDao.saveNotice(notice);
    }

    @Override
    public Notice getNoticeByNoticeId(Integer noticeId) {

        User user = userService.getCurrentUser();

        Notice notice = noticeDao.getNoticeByNoticeId(noticeId);

        /*
        *
        * 以下几种情况只要一种就会抛出权限拒绝异常
        * 1.公告为非公开公告且发送者不是当前用户
        * 2.公告为非公开公告且发送者不是当前用户的领导
        * */
        if(!isPublicNotice(notice)){
            if (!noticeBelongUser(notice, user) && !userAisUserBLeader(notice.getNoticeSender(), user)) {
                throw new PermissionDeniedException(DataDictionary.PERMISSION_DENIED.toString());
            }
        }

        return notice;



    }

    @Override
    public int updateNotice(NoticeDto noticeDto, Integer noticeId) {
        User user = userService.getCurrentUser();

        Notice notice = getNoticeByNoticeId(noticeId);

        if(notice == null){
            throw new IllegalArgumentException(DataDictionary.PARAM_ERROR.toString());
        }

        if(!noticeBelongUser(notice,user)){
            throw new PermissionDeniedException(DataDictionary.PERMISSION_DENIED.toString());
        }

        notice=noticeDtoToNotice(noticeDto,user);

        notice.setNoticeId(noticeId);
        return noticeDao.updateNotice(notice);


    }

    @Override
    public int deleteNoticeByNoticeId(Integer noticeId, User user) {
        Notice notice=getNoticeByNoticeId(noticeId);

        return noticeDao.deleteNoticeByNoticeId(noticeId);


    }

    @Override
    public List<Notice> searchNoticeListByPage(User user, String keyword,Page page) {

        return noticeDao.searchNoticeListByPage(user.getUserId(),keyword,page);
    }

    private boolean isPublicNotice(Notice notice){
        return !notice.getOnlyStaff();
    }

    private boolean noticeBelongUser(Notice notice,User user){
        return notice.getNoticeSender().getUserId().equals(user.getUserId());
    }

    private boolean userAisUserBLeader(User userA,User userB){
        return userA.getUserId().equals(userB.getUserInfo().getLeader().getUserId());
    }


    private Notice noticeDtoToNotice(NoticeDto noticeDto,User user){
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeDto.getNoticeTitle());
        notice.setNoticeContent(noticeDto.getNoticeContent());
        notice.setOnlyStaff(noticeDto.getOnlyStaff());
        notice.setNoticeSender(user);
        return notice;
    }


}
