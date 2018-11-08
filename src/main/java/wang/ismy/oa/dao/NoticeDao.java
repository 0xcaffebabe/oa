package wang.ismy.oa.dao;

import org.apache.ibatis.annotations.Param;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;

import java.util.List;

public interface NoticeDao {

    List<Notice> getNoticeListByLeaderId(Integer userId);

    List<Notice> getPublicNoticeList();

    List<Notice> getNoticeListByUserIdByPage(@Param("userId") Integer userId, @Param("page")Page page);

    int saveNotice(Notice notice);

    Notice getNoticeByNoticeId(Integer noticeId);

    int updateNotice(Notice notice);

    int deleteNoticeByNoticeId(Integer noticeId);

    List<Notice> searchNoticeListByPage(@Param("userId") Integer userId, @Param("keyword")
            String keyword, @Param("page") Page page);

}
