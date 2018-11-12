package wang.ismy.oa.common;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Holiday {

    /*休息日*/
    private List<LocalDate> holidayList=new ArrayList<>();

    /*工作日*/
    private List<LocalDate> workDayList=new ArrayList<>();

    /*节日描述*/
    private String description;

    /*
    * @param baseDate 放假基准日期
    * @param holidays 放假天数
    * */
    public Holiday setHolidayRange(LocalDate baseDate,int holidays){
        holidayList.add(baseDate);
        for(int i = 1;i<holidays;i++){
            holidayList.add(baseDate.plusDays(i));
        }

        return this;
    }


    /*
     * @param baseDate 工作日基准日期
     * @param holidays 工作日天数
     * */
    public Holiday setWorkDayRange(LocalDate baseDate,int workDays){
        workDayList.add(baseDate);

        for(int i = 1;i<workDays;i++){
            workDayList.add(baseDate.plusDays(i));
        }
        return this;
    }

    public Holiday(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /*
    * 判断传入的日期是否应该放假
    * */
    public boolean isHoliday(LocalDate date){

        for(LocalDate localDate:holidayList){
            if(localDate.equals(date)){
                return true;
            }
        }
        return false;
    }

    /*
    * 判断传入的日期是否在调休日
    * */
    public boolean inAdjustDay(LocalDate date){
        for(LocalDate localDate:workDayList){
            if(localDate.equals(date)){
                return true;
            }
        }
        return false;
    }

}
