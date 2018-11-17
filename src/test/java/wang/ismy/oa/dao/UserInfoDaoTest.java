package wang.ismy.oa.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.entity.UserInfo;

import static org.junit.Assert.*;

public class UserInfoDaoTest extends BaseTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void getUserInfoByUserId() {

        UserInfo userInfo=userInfoDao.getUserInfoByUserId(1);

        System.err.println(userInfo);
    }

    /*
    * admin
    * */
    @Test
    public void userProfile(){

        assertEquals(1,userInfoDao.updateUserProfile(3,"http://123"));
    }
}