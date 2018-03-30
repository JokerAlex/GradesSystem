package com.grades.mapping;


import com.grades.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserByName(){
        User user=userMapper.findUserByName("test","111");
        System.out.println(user.getId()+user.getName()+user.getUserName());
    }

    @Test
    public void findUserByEmail(){
        User user = userMapper.findUserByEmail("haha@163.com","111");
        System.out.println(user.getId()+user.getName()+user.getUserName());
    }

    @Test
    public void findUserName(){
        String username=userMapper.findUserName("test");
        String username2=userMapper.findUserName("haha");
        //if (username2.equals("null"))
            System.out.println("String"+username2);
        if (username2==null)
            System.out.println("object"+username2);
    }

    @Test
    public void insertUser(){
        User user = new User("test3","zzz","zxc","zxc@qq.com",22,"2016");
        boolean flag=userMapper.insertUser(user);
        System.out.println(flag);
    }

    @Test
    public void updateLastLogin(){
        User user = new User();
        user.setId(1);
        user.setLoginTime(new Date().toString());
        System.out.println(userMapper.updateLastLoginTime(user));
    }

    @Test
    public void changPwd(){
        System.out.println(userMapper.changPwd("222",1));
    }

    @Test
    public void updateUserInfo(){
        User user = new User();
        user.setId(1);
        user.setName("cc");
        user.setEmail("haha@163.com");
        user.setCollegeId(22);
        user.setGrade("2017");
        System.out.println(userMapper.updateUserInfo(user));
    }

    @Test
    public void checkPwd(){
        String userId = userMapper.checkPwd(1,"111");
        if (userId == null)
            System.out.println("object is null"+userId);
        else
            System.out.println("String is null"+userId);

    }
}
