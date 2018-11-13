package wang.ismy.oa.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Message implements Serializable {

    private Integer messageId;

    private User messageTo;

    private User messageFrom;

    private String messageContent;

    private Boolean hasRead;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;


}
