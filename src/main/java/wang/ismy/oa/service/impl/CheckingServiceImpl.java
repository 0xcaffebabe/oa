package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dao.CheckingDao;
import wang.ismy.oa.dto.CheckingDto;
import wang.ismy.oa.dto.CheckingTimeDto;
import wang.ismy.oa.entity.Checking;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.enums.CheckingEnum;
import wang.ismy.oa.enums.CheckingTimeEnum;
import wang.ismy.oa.exception.InvalidCheckingTimeException;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.exception.RepeatCheckingException;
import wang.ismy.oa.service.CheckingService;
import wang.ismy.oa.service.UserService;
import wang.ismy.oa.util.TimeUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingDao checkingDao;

    @Autowired
    private UserService userService;
    @Override
    public int addCheckingRecord() throws NotLoginException {
        Checking checking=new Checking();
        LocalTime time=LocalTime.now();

        //如果时间位于0点到 上班时间期间,则禁止打卡
        if(TimeUtils.isBetween(time,LocalTime.of(0,0),CheckingTimeEnum.ON_DUTY.getTime())){
            throw new InvalidCheckingTimeException(DataDictionary.INVALID_CHECKING_TIME.toString());
        }

        //如果位于上班时间到下班时间之间 ,则上班 否则下班
        CheckingEnum checkingEnum;

        if(TimeUtils.isBetween(time,
                CheckingTimeEnum.ON_DUTY.getTime(),
                CheckingTimeEnum.OFF_DUTY.getTime())){

            checkingEnum=CheckingEnum.ON_DUTY;
            //如果今天已有上班打过卡:
            if(hasDutyState(CheckingEnum.ON_DUTY)){
                throw new RepeatCheckingException(DataDictionary.REPEAT_CHECKING.toString());
            }
        }else{
            checkingEnum=CheckingEnum.OFF_DUTY;
            if(hasDutyState(CheckingEnum.OFF_DUTY)){
                throw new RepeatCheckingException(DataDictionary.REPEAT_CHECKING.toString());
            }
        }

        checking.setCheckingUser(userService.getCurrentUser());
        checking.setCheckingCreateTime(new Date());
        checking.setCheckingDate(new Date());
        checking.setCheckingType(checkingEnum);
        return checkingDao.addCheckingRecord(checking);
    }

    @Override
    public CheckingTimeDto getCheckingTime() {


        LocalDateTime onDutyTime=LocalDateTime.of(LocalDate.now(),CheckingTimeEnum.ON_DUTY.getTime());
        LocalDateTime offDutyTime=LocalDateTime.of(LocalDate.now(),CheckingTimeEnum.OFF_DUTY.getTime());
        CheckingTimeDto checkingTimeDto=new CheckingTimeDto();
        checkingTimeDto.setOffDutyTime(offDutyTime);
        checkingTimeDto.setOnDutyTime(onDutyTime);
        return checkingTimeDto;


    }

    @Override
    public boolean hasDutyState(CheckingEnum state) throws NotLoginException {
        User user=userService.getCurrentUser();
        return checkingDao.getTodayDutyStateByUserIdAndDutyState(user.getUserId(),state);
    }

    @Override
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    @Override
    public List<Checking> getSelfRecentCheckingList() throws NotLoginException {
        User user=userService.getCurrentUser();
        return checkingDao.getRecentCheckingListByUserId(user.getUserId());
    }
}
