package wang.ismy.oa.util;

import java.time.LocalTime;

public class TimeUtils {

//    判断时间1是否在时间2以及时间3之间
    public static boolean isBetween(LocalTime time1,LocalTime time2,LocalTime time3){

        return time1.isAfter(time2) && time1.isBefore(time3);
    }
}
