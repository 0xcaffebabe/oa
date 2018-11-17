package wang.ismy.oa.dao;


import org.apache.ibatis.annotations.Param;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.User;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);

    User getUserByUserId(Integer userId);

    List<User> getStaffListByPage(@Param("userId") Integer userId, @Param("page") Page page);

}
