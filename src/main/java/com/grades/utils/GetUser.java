package com.grades.utils;

import javax.servlet.http.Cookie;

public class GetUser {
    public static String getUser(Cookie[] cookies){
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("JSESSIONID")){
                return cookie.getValue();
            }
        }
        return null;
    }
}
