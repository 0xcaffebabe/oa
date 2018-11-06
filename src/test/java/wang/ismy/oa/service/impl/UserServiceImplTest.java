package wang.ismy.oa.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.entity.User;

import static org.junit.Assert.*;

public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserServiceImpl userService;
    @Test
    public void getUser() {

        User user=userService.getUser("JS002");

        System.err.println(user);
    }

    @Test
    public void getUser1() {
    }
}