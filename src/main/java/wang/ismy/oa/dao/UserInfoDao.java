package wang.ismy.oa.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import wang.ismy.oa.entity.UserInfo;

public interface UserInfoDao {


    UserInfo getUserInfoByUserId(Integer userId);

    int updateUserProfile(@Param("userId") Integer userId, @Param("imgUrl") String imgUrl);
}
