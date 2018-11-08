package wang.ismy.oa.service;

import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;
import wang.ismy.oa.dto.NoticeDto;
import wang.ismy.oa.entity.User;

import java.util.List;

public interface NoticeService {

    List<Notice> getNoticeListByLeader(User leader);

    List<Notice> getNoticeListByUserId(Integer userId, Page page);

    int saveNotice(NoticeDto noticeDto,User user);

    Notice getNoticeByNoticeId(Integer noticeId);

    int updateNotice(NoticeDto noticeDto,Integer noticeId);

    int deleteNoticeByNoticeId(Integer noticeId,User user);

    List<Notice> searchNoticeListByPage(User user,String keyword,Page page);
}
