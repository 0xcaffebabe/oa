package wang.ismy.oa.entity;

import lombok.Data;
import wang.ismy.oa.enums.CheckingEnum;

import java.util.Date;

@Data
public class Checking {

    private Integer checkingId;

    private Date checkingDate;

    private CheckingEnum checkingType; //考勤类型：真为上班 假为下班

    private User checkingUser;

    private Date checkingCreateTime;

}
