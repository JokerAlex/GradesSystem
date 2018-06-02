package com.grades.interceptor;

import com.grades.model.User;
import com.grades.utils.GetUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取session
        String sessionId = GetUser.getUser(httpServletRequest.getCookies());
        System.out.println("intereceptor--------->sessionId---->"+sessionId);
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute(sessionId);
        if (user != null){
            return true;
        }
        //不符合条件的，跳转到登陆界面
        httpServletResponse.sendRedirect("/GradesSystem/views/login.html");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
