package wang.ismy.oa.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Event {

    private Integer eventId;

    private String eventName;

    private String eventDesc;

    private LocalDateTime eventStartTime;

    private LocalDateTime eventEndTime;

    private User eventUser;

    private Date eventCreateTime;




}
