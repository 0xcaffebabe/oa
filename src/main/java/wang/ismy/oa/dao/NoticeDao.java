package wang.ismy.oa.dao;

import org.apache.ibatis.annotations.Param;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;

import java.util.List;

public interface NoticeDao {

    List<Notice> getNoticeListByLeaderId(Integer userId);

    List<Notice> getPublicNoticeList();

    List<Notice> getNoticeListByUserIdByPage(@Param("userId") Integer userId, @Param("page")Page page);

}
