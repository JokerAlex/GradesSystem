package com.grades.service;

import com.grades.model.User;

public interface MaintainService {

    String changPwd(String userId, String oldPwd, String newPwd);

    boolean updateUserInfo(User user);

    String delCollege(String collegeId);

    String updateCollege(String collegeId, String collegeName, String collegeIdOld);
}
