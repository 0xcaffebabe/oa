package wang.ismy.oa.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Notice;

import java.util.List;

import static org.junit.Assert.*;

public class NoticeDaoTest extends BaseTest {

    @Autowired
    private NoticeDao noticeDao;

    @Test
    public void getNoticeListByLeaderId() {

        List<Notice> list=noticeDao.getNoticeListByLeaderId(1);

        System.err.println(list);
    }

    @Test
    public void getNoticeListByUserIdByPage(){

        assertEquals(2,noticeDao.getNoticeListByUserIdByPage(1,new Page(1,10)).size());
    }

    /*
    * admin
    * */
    @Test
    public void search(){
        assertEquals(2,noticeDao.searchNoticeListByPage(2,"廖师兄",new Page(1,10)));
    }
}