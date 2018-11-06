package wang.ismy.oa.dao;


import wang.ismy.oa.entity.User;

public interface UserDao {

    User getUserByUsername(String username);

    User getUserByUserId(Integer userId);
}
