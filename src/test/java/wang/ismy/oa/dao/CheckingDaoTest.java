package wang.ismy.oa.dao;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.enums.CheckingEnum;

import static org.junit.Assert.*;

public class CheckingDaoTest extends BaseTest {

    @Autowired
    private CheckingDao checkingDao;
    @Test
    public void getTodayOnDutyStateByUserId() {


        assertEquals(1,checkingDao.getRecentCheckingListByUserId(2).size());
    }
}