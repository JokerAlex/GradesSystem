package com.grades.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.grades.mapping.CollegeMapper;
import com.grades.mapping.GradeMapper;
import com.grades.mapping.UserMapper;
import com.grades.model.College;
import com.grades.model.Grade;
import com.grades.model.User;
import com.grades.service.LoginService;
import com.grades.utils.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    private UserMapper userMapper;
    private CollegeMapper collegeMapper;
    private GradeMapper gradeMapper;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, CollegeMapper collegeMapper, GradeMapper gradeMapper){
        this.userMapper=userMapper;
        this.collegeMapper = collegeMapper;
        this.gradeMapper = gradeMapper;
    }

    //用户名密码登录
    public User loginByUserName(String userName, String userPwd) {
        User user;
        if (userName != null && !userName.equals("")){
            //加密密码对比
            String pw = PasswordEncrypt.encrypt(userPwd);
            user = userMapper.findUserByName(userName,pw);
            if (user != null){
                user.setLoginTime(new Date().toString());
            }
            return user;
        }
        return null;
    }

    //用户名邮箱登录
    public User loginByUserEmail(String userEmail, String userPwd) {
        User user;
        if (userEmail != null && !userEmail.equals("")){
            String pw = PasswordEncrypt.encrypt(userPwd);
            user = userMapper.findUserByEmail(userEmail,pw);
            if (user != null){
                user.setLoginTime(new Date().toString());
            }
            return user;
        }
        return null;
    }

    //获取登录时间
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
        boolean isUserNameAvailable;
        boolean isRegister;
        int status;
        String resultMsg;
        if (user != null){
            isUserNameAvailable = isUserNameAvailable(user.getUserName());
            if (isUserNameAvailable){
                String tempPw = PasswordEncrypt.encrypt(user.getPassWd());
                user.setPassWd(tempPw);
                isRegister = userMapper.insertUser(user);
                if (isRegister){
                    status = 1;
                    resultMsg = "注册成功！";
                }else {
                    status = -3;
                    resultMsg = "注册失败！";
                }
            }else{
                status = -2;
                resultMsg = "用户名不可用！";
            }
        }else {
            status = -1;
            resultMsg = "参数错误！";
        }
        String result = "{\"status\":\""+status+"\",\"resultMsg\":\""+resultMsg+"\"}";
        return result;
    }

    public List<College> getAllColleges() {
        return collegeMapper.getAllColleges();
    }

    public List<Grade> getAllGrades() {
        return gradeMapper.getAllGrades();
    }
}
