package wang.ismy.oa.common;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HolidayValid {

    /*今年假日列表*/
    private List<Holiday> thisYearHolidays=new ArrayList<>();

    public HolidayValid(List<Holiday> thisYearHolidays) {
        this.thisYearHolidays = thisYearHolidays;
    }

    public HolidayValid(){
        init();
    }

    private void init(){
        //添加今年的节假日
        thisYearHolidays.add(new Holiday("元旦")
                .setHolidayRange(LocalDate.now().withMonth(1).withDayOfMonth(1),1)
        );

        thisYearHolidays.add(new Holiday("春节")
                .setHolidayRange(LocalDate.now().withMonth(2).withDayOfMonth(15),7)
                .setWorkDayRange(LocalDate.now().withDayOfMonth(2).withDayOfMonth(11),1)
                .setWorkDayRange(LocalDate.now().withDayOfMonth(2).withDayOfMonth(24),1)
        );

        thisYearHolidays.add(new Holiday("清明")
                .setHolidayRange(LocalDate.now().withMonth(4).withDayOfMonth(5),3)
                .setWorkDayRange(LocalDate.now().withMonth(4).withDayOfMonth(8),1)
        );

        thisYearHolidays.add(new Holiday("劳动节")
                .setHolidayRange(LocalDate.now().withMonth(4).withDayOfMonth(29),3)
                .setWorkDayRange(LocalDate.now().withMonth(4).withDayOfMonth(28),1)
        );

        thisYearHolidays.add(new Holiday("端午")
                .setHolidayRange(LocalDate.now().withMonth(6).withDayOfMonth(18),1)
        );

        thisYearHolidays.add(new Holiday("中秋")
                .setHolidayRange(LocalDate.now().withMonth(9).withDayOfMonth(24),1)
        );

        thisYearHolidays.add(new Holiday("国庆")
                .setHolidayRange(LocalDate.now().withMonth(10).withDayOfMonth(1),7)
                .setWorkDayRange(LocalDate.now().withMonth(9).withDayOfMonth(29),2)
        );



    }

    public boolean todayIsHoliday(){
        LocalDate today=LocalDate.now();
        return shouldBeHoliday(today);
    }

    public boolean shouldBeHoliday(LocalDate localDate){
        //首先判断是否是节假日
        if(isHoliday(localDate)){
            return true;
        }

        //判断不在调休日并且今天是周末
        return !inAdjustDay(localDate) && isWeekend(localDate);
    }

    private boolean isHoliday(LocalDate date){
        //循环判断date是否在holidayList 里
        for(Holiday holiday:thisYearHolidays){
            if(holiday.isHoliday(date)){
                return true;
            }
        }
        return false;
    }

    private boolean inAdjustDay(LocalDate date){

        for(Holiday holiday:thisYearHolidays){
            if(holiday.inAdjustDay(date)){
                return true;
            }
        }
        return false;
    }

    private boolean isWeekend(LocalDate date){
        return date.getDayOfWeek()==DayOfWeek.SATURDAY ||
                date.getDayOfWeek()==DayOfWeek.SUNDAY;
    }

    public static void main(String[] args) {
        HolidayValid holidayValid =new HolidayValid();
        System.out.println(holidayValid.todayIsHoliday());
    }
}
