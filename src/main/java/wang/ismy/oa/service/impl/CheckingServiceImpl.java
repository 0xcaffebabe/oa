package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.common.HolidayValid;
import wang.ismy.oa.dao.CheckingDao;
import wang.ismy.oa.dto.CheckingDto;
import wang.ismy.oa.dto.CheckingInfo;
import wang.ismy.oa.dto.CheckingTimeDto;
import wang.ismy.oa.dto.Page;
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
import java.util.*;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingDao checkingDao;

    private HolidayValid holidayValid=new HolidayValid();

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
        checking.setCheckingCreateTime(LocalDateTime.now());
        checking.setCheckingDate(LocalDateTime.now());
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

    @Override
    public CheckingInfo getCheckingInfoByUserId(Integer userId) {
        CheckingInfo info = new CheckingInfo();
        /*获取最近一个月的用户打卡记录*/
        List<Checking> checkingList = getCheckingListByUserId(userId);

        info.setCheckingRate(calcCheckingRate(checkingList));
        info.setOnTimeRate(calcCheckingOnTimeRate(checkingList));
        info.setAvgWorkDuration((double) calcAvgWorkTime(checkingList));
        return info;

    }

    @Override
    public CheckingInfo getCurrentUserCheckingInfo() {
        User user = userService.getCurrentUser();
        return getCheckingInfoByUserId(user.getUserId());
    }

    @Override
    public List<Checking> getCheckingListByUserIdByPage(Integer userId, Page page) {
        return checkingDao.getCheckingListByUserIdByPage(userId,page);
    }

    /*计算最近一个月用户考勤率
    *
    * 计算公式:最近一个月总打卡次数/应打卡次数
    * 说明:应打卡次数=最近一个月工作日数*2
    *
    * */
    private double calcCheckingRate(List<Checking> checkingRecord){

        return (double)getCheckingTimeByUserId(checkingRecord)/(getWorkDays()*2)*100;
    }

    /*
    * 计算最近一个月用户考勤准时率
    *
    * 计算方法:准时次数/总考勤次数
    * 说明：准时的考量为：基本打卡时间+10分钟内
    *
    * */
    private double calcCheckingOnTimeRate(List<Checking> checkingRecord){
        int onTimeCount=0;

        for(Checking checking: checkingRecord){
            //如果晚于上班时间，早于下班时间
            if(TimeUtils.isBetween(checking.getCheckingDate().toLocalTime(),
                    CheckingTimeEnum.ON_DUTY.getTime(),
                    CheckingTimeEnum.OFF_DUTY.getTime())){
                //如果打卡时间小于8:10
                if(checking.getCheckingDate().toLocalTime().compareTo(CheckingTimeEnum.ON_DUTY.getTime().withMinute(10))<0){
                    onTimeCount++;
                }
            }else{
                //如果打卡时间小于17:10
                if(checking.getCheckingDate().toLocalTime().compareTo(CheckingTimeEnum.OFF_DUTY.getTime().withMinute(10))<0){
                    onTimeCount++;
                }
            }
        }
        return (double)onTimeCount/ checkingRecord.size()*100;
    }

    /*
    * 计算最近一个月平均工作时间
    *
    * 计算方法:每日工作时间之和/工作天数
    * */
    private long calcAvgWorkTime(List<Checking> checkingRecord){

        Map<Integer,List<LocalDateTime>> workTimeMapping = new HashMap<>();
        long totalWorkTime = 0;

        /*
        * 这段代码的作用为按天对考勤日期进行分类
        * */
        for(Checking checking: checkingRecord){
            Integer key=checking.getCheckingDate().getDayOfYear();
            if(!workTimeMapping.containsKey(key)){
                workTimeMapping.put(key,new ArrayList<>());
            }
            workTimeMapping.get(key).add(checking.getCheckingDate());
        }

        for(Integer key:workTimeMapping.keySet()){
            long workTime=0;
            if(!(workTimeMapping.get(key).size()<2)){
                workTime=TimeUtils.timeDiff(workTimeMapping.get(key).get(0),workTimeMapping.get(key).get(1));
            }
            totalWorkTime+=workTime;
        }

        return totalWorkTime/workTimeMapping.keySet().size();

    }

    /*获取最近一个月工作日数*/
    private int getWorkDays(){
        LocalDate localDate=LocalDate.now();

        int workdays=0;
        for(int i=0;i<=30;i++){
            if(!holidayValid.shouldBeHoliday(localDate.plusDays(-i))){
                workdays++;
            }

        }
        return workdays;
    }

    /*获取最近一个月某个用户的打卡次数*/
    private int getCheckingTimeByUserId(List<Checking> checkingRecord){
        return checkingRecord.size();
    }

    /*获取最近一个月考勤记录*/
    private List<Checking> getCheckingListByUserId(Integer userId){
        return checkingDao.getCheckingListByUserId(userId);
    }



}
