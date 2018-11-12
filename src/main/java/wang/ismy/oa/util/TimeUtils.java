package wang.ismy.oa.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class TimeUtils {

//    判断时间1是否在时间2以及时间3之间
    public static boolean isBetween(LocalTime time1,LocalTime time2,LocalTime time3){

        return time1.isAfter(time2) && time1.isBefore(time3);
    }

    /*计算两个时间的时间差(秒)*/
    public static long timeDiff(LocalDateTime localDateTime1,LocalDateTime localDateTime2){
        return Math.abs(localDateTime1.toEpochSecond(ZoneOffset.UTC)-localDateTime2.toEpochSecond(ZoneOffset.UTC));
    }


}
