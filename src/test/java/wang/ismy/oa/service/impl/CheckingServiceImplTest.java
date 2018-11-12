package wang.ismy.oa.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.service.CheckingService;

import static org.junit.Assert.*;

public class CheckingServiceImplTest extends BaseTest {

    @Autowired
    private CheckingService checkingService;

    @Test
    public void calcCheckingRate() {
        System.err.println(checkingService.getCheckingInfoByUserId(2));
    }

    /*
    * admin
    * */
    @Test
    public void bypage(){
        assertEquals(10,checkingService.getCheckingListByUserIdByPage(2,new Page(1,10)).size());
    }
}