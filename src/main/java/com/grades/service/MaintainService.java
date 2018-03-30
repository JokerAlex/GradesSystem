package com.grades.service;

import com.grades.model.User;

public interface MaintainService {
    /**
    * @description:修改用户密码
    * @Param: [userId, oldPwd, newPwd]
    * @return: java.lang.String
    * @Author: Alex
    * @Date: 2018/3/30
    */
    String changPwd(String userId, String oldPwd, String newPwd);
    /**
    * @description:用户信息修改
    * @Param: [user]
    * @return: java.lang.String
    * @Author: Alex
    * @Date: 2018/3/30
    */
    boolean updateUserInfo(User user);
}
