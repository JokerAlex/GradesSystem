package com.grades.controller;


import com.grades.model.User;
import com.grades.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/GradesSystem")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public User doLogin(@RequestBody User user, HttpSession session){
        User userLogin = loginService.loginByUserName(user.getUserName(),user.getPassWd());
        System.out.println(userLogin.getCollege());
        session.setAttribute("user",userLogin);
        return userLogin;
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String doRegister(@RequestBody User user){
        String registerResult = loginService.register(user);
        return "{\"isRegister\":\""+registerResult+"\"}";
    }


}
