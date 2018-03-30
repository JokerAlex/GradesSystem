package com.grades.service;

import com.grades.model.User;

public interface LoginService {

    /**
    * @Param: [userId, userPwd]
    * @return: com.grades.model.User
    * @Author: Alex
    * @Date: 2018/3/28
    */
    User loginByUserName(String userName,String userPwd);
    /**
    * @Param: [userEmail, userPwd]
    * @return: com.grades.model.User
    * @Author: Alex
    * @Date: 2018/3/28
    */
    User loginByUserEmail(String userEmail,String userPwd);
    /**
    * @Description:用户登录时间
    * @Param: [user]
    * @return: void
    * @Author: Alex
    * @Date: 2018/3/30
    */
    void loginTime(User user);
    /**
    * @description:检查新注册用户名是否可用
    * @Param: [userName]
    * @return: boolean
    * @Author: Alex
    * @Date: 2018/3/30
    */
    boolean checkUserName(String userName);
    /**
    * @description:注册
    * @Param: [user]
    * @return: boolean
    * @Author: Alex
    * @Date: 2018/3/30
    */
    boolean register(User user);


}
