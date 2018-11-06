package wang.ismy.oa.dto;

import lombok.Data;
import wang.ismy.oa.valid.CustomEmail;
import wang.ismy.oa.valid.CustomNotNull;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {


    @CustomNotNull
    private String username;

    @CustomNotNull
    private String password;
}
