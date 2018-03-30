package com.grades.serviceImpl;

import com.grades.mapping.UserMapper;
import com.grades.model.User;
import com.grades.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    private UserMapper userMapper;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public User loginByUserName(String userName, String userPwd) {
        User user;
        if (userName != null && !userName.equals("")){
            user = userMapper.findUserByName(userName,userPwd);
            user.setLoginTime(new Date().toString());
            return user;
        }
        return null;
    }

    public User loginByUserEmail(String userEmail, String userPwd) {
        User user;
        if (userEmail != null && !userEmail.equals("")){
            user = userMapper.findUserByEmail(userEmail,userPwd);
            user.setLoginTime(new Date().toString());
            return user;
        }
        return null;
    }

    public void loginTime(User user) {
        if (user != null){
            userMapper.updateLastLoginTime(user);
        }
    }

    public boolean checkUserName(String userName) {
        if (userName == null){
            return false;
        }
        String findResult = userMapper.findUserName(userName);
        if (findResult == null)
            return true;
        return false;
    }

    public boolean register(User user) {
        if (user != null){
            return userMapper.insertUser(user);
        }
        return false;
    }
}
