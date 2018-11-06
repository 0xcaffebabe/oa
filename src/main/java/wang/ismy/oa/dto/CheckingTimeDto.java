package wang.ismy.oa.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckingTimeDto {

    private LocalDateTime onDutyTime;

    private LocalDateTime offDutyTime;


}
