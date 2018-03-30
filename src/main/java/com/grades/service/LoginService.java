package com.grades.service;

import com.grades.model.User;

public interface LoginService {


    User loginByUserName(String userName,String userPwd);

    User loginByUserEmail(String userEmail,String userPwd);

    void loginTime(User user);

    boolean isUserNameAvailable(String userName);

    String register(User user);


}
