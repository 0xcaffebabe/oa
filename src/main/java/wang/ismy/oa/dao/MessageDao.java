package wang.ismy.oa.dao;

import org.apache.ibatis.annotations.Param;
import wang.ismy.oa.dto.Page;
import wang.ismy.oa.entity.Message;

import java.util.List;

public interface MessageDao {

    int saveMessage(Message message);

    List<Message> getMessageListByPage(@Param("userId") Integer userId, @Param("fromUser")Integer fromUser, @Param("page")Page page);
}
