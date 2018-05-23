package com.grades.service;

import com.grades.model.*;

import java.util.List;

public interface LoginService {


    User loginByUserName(String userName,String userPwd);

    User loginByUserEmail(String userEmail,String userPwd);

    void loginTime(User user);

    boolean isUserNameAvailable(String userName);

    public String checkUserName(String userName);

    String register(User user);

    List<College> getAllColleges();

    List<Grade> getAllGrades();


}
