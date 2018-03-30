package com.grades.serviceImpl;

import com.grades.mapping.UserMapper;
import com.grades.model.User;
import com.grades.service.LoginService;
import com.grades.utils.PasswordEncrypt;
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
            //加密密码对比
            String pw = PasswordEncrypt.encrypt(userPwd);
            user = userMapper.findUserByName(userName,pw);
            user.setLoginTime(new Date().toString());
            return user;
        }
        return null;
    }

    public User loginByUserEmail(String userEmail, String userPwd) {
        User user;
        if (userEmail != null && !userEmail.equals("")){
            String pw = PasswordEncrypt.encrypt(userPwd);
            user = userMapper.findUserByEmail(userEmail,pw);
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

    public boolean isUserNameAvailable(String userName) {
        if (userName == null){
            return false;
        }
        String findResult = userMapper.findUserName(userName);
        if (findResult == null)
            return true;
        return false;
    }

    public String register(User user) {
        boolean isError = true;
        boolean isUserNameAvailable = false;
        boolean isRegister = false;
        if (user != null){
            isError = false;
            isUserNameAvailable = isUserNameAvailable(user.getUserName());
            if (isUserNameAvailable){
                String tempPw = PasswordEncrypt.encrypt(user.getPassWd());
                user.setPassWd(tempPw);
                isRegister = userMapper.insertUser(user);
            }
        }
        return "{\"isError\":\""+isError+"\",\"isUserNameAvailable\":\""+isUserNameAvailable+"\",\"isRegister\":\""+isRegister+"\"}";
    }
}
