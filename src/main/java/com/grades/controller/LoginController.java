package com.grades.controller;


import com.alibaba.fastjson.JSONObject;
import com.grades.model.*;
import com.grades.service.LoginService;
import com.grades.utils.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"user"})
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject doLogin(@RequestBody Map<String,String> userMap, HttpServletRequest request){
        int loginCode = -1;
        String resultMsg;
        JSONObject jsonObject = new JSONObject();

        User userByUserName = loginService.loginByUserName(userMap.get("userName"),userMap.get("passWd"));
        User userByUserEmail = loginService.loginByUserEmail(userMap.get("userName"),userMap.get("passWd"));

        User userLogin;
        if (userByUserName != null){
            userLogin = userByUserName;
        } else {
            userLogin = userByUserEmail;
        }

        if (userLogin != null){
            loginService.loginTime(userLogin);
            loginCode = 1;
            resultMsg = "登录成功";
        }else {
            resultMsg = "用户名或密码错误";
        }
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(30*60);//设置过期时间
        String sessionId = session.getId();
        session.setAttribute(sessionId,userLogin);

        jsonObject.put("loginCode",loginCode);
        jsonObject.put("resultMsg",resultMsg);
        jsonObject.put("user",userLogin);
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public String doLogout(HttpServletRequest request){
        request.getSession().removeAttribute(request.getSession().getId());
        return "{\"resultCode\":\"1\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/checkUserName")
    public String checkUserName(@RequestBody String userName){
        return loginService.checkUserName(userName);
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
