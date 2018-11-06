package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dao.UserDao;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.exception.NotLoginException;
import wang.ismy.oa.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String username) {
        User user=userDao.getUserByUsername(username);
        if(user!=null){
            //如果此用户拥有领导
            if(user.getUserInfo().getLeader()!=null){
                User leader=userDao.getUserByUserId(user.getUserInfo().getLeader().getUserId());
                user.getUserInfo().setLeader(leader);
            }
        }
        return user;
    }

    @Override
    public User getUser(Integer userId) {
        return null;
    }

    @Override
    public User getCurrentUser() throws NotLoginException {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);

        Object user=session.getAttribute("user");

        if(user!=null){
            if(user instanceof User){
                return (User)user;
            }
        }
        throw new NotLoginException(DataDictionary.NOT_LOGIN.toString());

    }
}
