package wang.ismy.oa.dto;

import lombok.Data;
import wang.ismy.oa.valid.CustomNotNull;

@Data
public class NoticeDto {

    @CustomNotNull
    private String noticeTitle;

    @CustomNotNull
    private String noticeContent;

    @CustomNotNull
    private Boolean onlyStaff;
}
