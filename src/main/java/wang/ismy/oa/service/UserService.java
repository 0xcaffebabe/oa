package wang.ismy.oa.service;

import wang.ismy.oa.entity.User;
import wang.ismy.oa.exception.NotLoginException;

public interface UserService {

    User getUser(String username);

    User getUser(Integer userId);

//    获得当前请求的用户
//    如果当前请求未登录，会抛出NotLoginException
    User getCurrentUser() throws NotLoginException;


}
