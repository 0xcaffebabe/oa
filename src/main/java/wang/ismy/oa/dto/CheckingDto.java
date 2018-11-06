package wang.ismy.oa.dto;

import lombok.Data;
import wang.ismy.oa.enums.CheckingEnum;
import wang.ismy.oa.valid.CustomNotNull;

import javax.validation.constraints.NotNull;

@Data
public class CheckingDto {

    @CustomNotNull
    private Boolean checkingType;
}
