package wang.ismy.oa.dto;

import lombok.Data;

@Data
public class CheckingInfo {

    /*考勤率*/
    private Double checkingRate;

    /*准时率*/
    private Double onTimeRate;

    /*平均工作时长*/
    private Double avgWorkDuration;


}
