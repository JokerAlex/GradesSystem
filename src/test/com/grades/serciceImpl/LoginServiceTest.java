package com.grades.serciceImpl;


import com.grades.model.User;
import com.grades.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;


    @Test
    public void doLogin(){
        User user = loginService.loginByUserName("test","111");
        System.out.println(user.getCollege());
    }

    @Test
    public void doRegister(){
        User user = new User("test","zjc","111","163@163.com",21,"2016",0);
        String result = loginService.register(user);
        System.out.println(result);
    }
}
