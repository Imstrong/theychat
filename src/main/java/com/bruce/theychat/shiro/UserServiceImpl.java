package com.bruce.theychat.shiro;

import com.bruce.theychat.user.entity.User;
import org.apache.shiro.authc.credential.PasswordService;

public class UserServiceImpl {
    private PasswordHelper passwordHelper=new PasswordHelper();
    private UserDao userDao=new UserDao();
    public User createUser(User user) {
//加密密码
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }
}
