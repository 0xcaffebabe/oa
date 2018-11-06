package wang.ismy.oa.enums;

import java.time.LocalTime;

public enum CheckingTimeEnum {

//    上下班时间
    ON_DUTY(LocalTime.of(8,0)),OFF_DUTY(LocalTime.of(17,0));
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    CheckingTimeEnum(LocalTime time) {

        this.time=time;
    }
}
