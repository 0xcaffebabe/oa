package wang.ismy.oa.service;

import wang.ismy.oa.dto.ImgFileDto;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.entity.UserInfo;

public interface UserInfoService {

    UserInfo getUserInfo(Integer userId);

    UserInfo getStaffUserInfo(Integer staffId);

    int updateUserProfile(User user, ImgFileDto dto);
}
