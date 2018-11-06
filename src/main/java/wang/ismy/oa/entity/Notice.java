package wang.ismy.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {

    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Boolean onlyStaff; //是否只有下级可见

    private User noticeSender;

    private Date createTime;

    private Date lastEditTime;
}
