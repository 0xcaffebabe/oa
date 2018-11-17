package wang.ismy.oa.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.dto.Page;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Test
    public void getStaffListByPage() {

        assertEquals(1,userDao.getStaffListByPage(1,new Page(1,10)).size());
    }
}