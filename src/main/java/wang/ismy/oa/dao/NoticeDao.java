package wang.ismy.oa.dao;

import wang.ismy.oa.entity.Notice;

import java.util.List;

public interface NoticeDao {

    List<Notice> getNoticeListByLeaderId(Integer userId);

    List<Notice> getPublicNoticeList();
}
