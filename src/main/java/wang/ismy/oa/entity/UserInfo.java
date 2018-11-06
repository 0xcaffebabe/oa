package wang.ismy.oa.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserInfo {

    private Integer userInfoId;

    private String fullName; //全名

    private Department department;

    private User leader;

    private String userProfile;

    private String idNumber;

    private String graduateSchool;

    private String position;

    private BigDecimal currentSalary;


}
