package wang.ismy.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Event {

    private Integer eventId;

    private String eventName;

    private String eventDesc;

    private Date eventStartTime;

    private Date eventEndTime;

    private User eventUser;

    private Date eventCreateTime;




}
