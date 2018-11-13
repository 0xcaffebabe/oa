package wang.ismy.oa.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MessageDto {

    @NotNull
    private Integer messageTo;

    @NotNull
    private String messageContent;

}
