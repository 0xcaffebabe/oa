package wang.ismy.oa.dto;

import lombok.Data;
import wang.ismy.oa.valid.CustomNotNull;

import java.time.LocalDateTime;

@Data
public class EventDto {

    @CustomNotNull
    private LocalDateTime eventStart;

    @CustomNotNull
    private LocalDateTime eventEnd;

    @CustomNotNull
    private String eventName;

    private Boolean eventAllDay;

    private String eventDesc;


}
