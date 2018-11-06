package wang.ismy.oa.dao;

import wang.ismy.oa.entity.UserInfo;

public interface UserInfoDao {


    UserInfo getUserInfoByUserId(Integer userId);
}
