package wang.ismy.oa.service;

import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;
import wang.ismy.oa.entity.User;

import java.util.List;

public interface NoticeService {

    List<Notice> getNoticeListByLeader(User leader);

    List<Notice> getNoticeListByUserId(Integer userId, Page page);
}
