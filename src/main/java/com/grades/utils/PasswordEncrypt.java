package com.grades.utils;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//密码加密
public class PasswordEncrypt {
    public static String encrypt(String originalPasswd){
        String encryptPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            encryptPassword = Base64.encode(messageDigest.digest(originalPasswd.getBytes()));

        }catch (NoSuchAlgorithmException nsae){
            nsae.printStackTrace();
        }
        return encryptPassword;
    }
}
