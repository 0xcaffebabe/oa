package wang.ismy.oa.service;

import org.springframework.http.ResponseEntity;
import wang.ismy.oa.dto.CheckingDto;
import wang.ismy.oa.dto.CheckingInfo;
import wang.ismy.oa.dto.CheckingTimeDto;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Checking;
import wang.ismy.oa.enums.CheckingEnum;
import wang.ismy.oa.exception.NotLoginException;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckingService {

    int addCheckingRecord() throws NotLoginException;

    CheckingTimeDto getCheckingTime();

//    是否拥有上班或者下班的某种状态
    boolean hasDutyState(CheckingEnum state) throws NotLoginException;

    LocalDateTime getCurrentDate();

    List<Checking> getSelfRecentCheckingList() throws NotLoginException;

    CheckingInfo getCheckingInfoByUserId(Integer userId);

    CheckingInfo getCurrentUserCheckingInfo();

    List<Checking> getCheckingListByUserIdByPage(Integer userId, Page page);

    List<Checking> getStaffCheckingListByPage(Integer staffId,Page page);

    ResponseEntity<byte[]> exportStaffCheckingListByPage(Integer staffId,Page page);



}
