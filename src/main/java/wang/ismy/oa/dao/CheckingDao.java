package wang.ismy.oa.dao;

import org.apache.ibatis.annotations.Param;
import wang.ismy.oa.dto.CheckingDto;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Checking;
import wang.ismy.oa.enums.CheckingEnum;

import java.util.List;

public interface CheckingDao {

    int addCheckingRecord(Checking checking);

    boolean getTodayDutyStateByUserIdAndDutyState(
            @Param("userId") Integer userId, @Param("state") CheckingEnum state);

    List<Checking> getRecentCheckingListByUserId(Integer userId);

    int getCheckingTimeByUserId(int userId);

    List<Checking> getCheckingListByUserId(Integer userId);

    List<Checking> getCheckingListByUserIdByPage(@Param("userId") Integer userId, @Param("page") Page page);
}
