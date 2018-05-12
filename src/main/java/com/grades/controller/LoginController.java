package com.grades.controller;


import com.grades.model.*;
import com.grades.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String doLogin(@RequestBody User user, HttpSession session){
        int status = -1;
        String resultMsg;
        User userLogin = loginService.loginByUserName(user.getUserName(),user.getPassWd());
        if (userLogin != null){
            loginService.loginTime(userLogin);
            status = 1;
            resultMsg = "登录成功";
        }else {
            resultMsg = "用户名或密码错误";
        }
        session.setAttribute("user",userLogin);
        return "{\"status\":\""+status+"\",\"resultMsg\":\""+resultMsg+"\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String doRegister(@RequestBody User user){
        String registerResult = loginService.register(user);
        return registerResult;
    }

    @ResponseBody
    @RequestMapping(value = "/college",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<College> getColleges(){
        return loginService.getAllColleges();
    }

    @ResponseBody
    @RequestMapping(value = "/grade",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Grade> getGrades(){
        return loginService.getAllGrades();
    }

}
