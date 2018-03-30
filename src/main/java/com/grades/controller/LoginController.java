package com.grades.controller;


import com.grades.model.User;
import com.grades.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/GradesSystem")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public ModelAndView doLogin(String userName,String userPwd){
        ModelAndView mav = new ModelAndView();
        User user = loginService.loginByUserName(userName, userPwd);
        if (user != null){
            mav.addObject("user",user);
            mav.setViewName("success");
            return mav;
        }
        mav.addObject("error","登录失败");
        mav.setViewName("error");
        return mav;
    }

}
