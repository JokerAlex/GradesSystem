package com.grades.service;

import com.grades.model.User;

public interface MaintainService {

    String changPwd(String userId, String oldPwd, String newPwd);

    boolean updateUserInfo(User user);
}
