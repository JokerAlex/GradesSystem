package com.grades.mapping;

import com.grades.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findUserByName(@Param("userName") String userName,@Param("userPwd") String userPwd);

    User findUserByEmail(@Param("userEmail") String userEmail,@Param("userPwd") String userPwd);

    String findUserName(@Param("userName") String userName);

    boolean insertUser(User user);

    String checkPwd(@Param("userId") int userId,@Param("userPwd") String userPwd);

    boolean changPwd(@Param("userPwd") String userPwd,@Param("userId") int userId);

    boolean updateUserInfo(User user);

    boolean updateLastLoginTime(User user);
}
