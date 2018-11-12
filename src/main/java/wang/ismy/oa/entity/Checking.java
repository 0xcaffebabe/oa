package wang.ismy.oa.entity;

import lombok.Data;
import wang.ismy.oa.enums.CheckingEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Checking {

    private Integer checkingId;

    private LocalDateTime checkingDate;

    private CheckingEnum checkingType; //考勤类型：真为上班 假为下班

    private User checkingUser;

    private LocalDateTime checkingCreateTime;

}
