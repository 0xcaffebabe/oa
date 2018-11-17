package wang.ismy.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.dao.UserInfoDao;
import wang.ismy.oa.dto.ImgFileDto;
import wang.ismy.oa.entity.User;
import wang.ismy.oa.entity.UserInfo;
import wang.ismy.oa.exception.PermissionDeniedException;
import wang.ismy.oa.exception.UserNotFoundException;
import wang.ismy.oa.service.UploadService;
import wang.ismy.oa.service.UserInfoService;
import wang.ismy.oa.service.UserService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadService uploadService;
    @Override
    public UserInfo getUserInfo(Integer userId) {
        return userInfoDao.getUserInfoByUserId(userId);
    }

    @Override
    public UserInfo getStaffUserInfo(Integer staffId) {
        User currentUser = userService.getCurrentUser();

        User staff = userService.getUser(staffId);

        if(staff != null){

            if(staff.getUserInfo().getLeader() != null) {

                if(staff.getUserInfo().getLeader().equals(currentUser)){
                    return userInfoDao.getUserInfoByUserId(staffId);
                }
            }
            throw new PermissionDeniedException(DataDictionary.PERMISSION_DENIED.toString());
        }
        throw new UserNotFoundException(DataDictionary.USER_NOT_EXIST.toString());
    }

    @Override
    public int updateUserProfile(User user, ImgFileDto dto) {
        String imgUrl = uploadService.uploadImg(dto);

        return userInfoDao.updateUserProfile(user.getUserId(),imgUrl);
    }

}
