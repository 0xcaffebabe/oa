package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.dao.UserInfoDao;
import wang.ismy.oa.entity.UserInfo;
import wang.ismy.oa.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo getUserInfo(Integer userId) {
        return userInfoDao.getUserInfoByUserId(userId);
    }

}
