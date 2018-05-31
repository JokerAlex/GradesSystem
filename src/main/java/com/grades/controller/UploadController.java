package com.grades.controller;


import com.grades.model.*;
import com.grades.serviceImpl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({"user"})
public class UploadController {

    private UploadServiceImpl uploadServiceImpl;

    @Autowired
    public UploadController(UploadServiceImpl uploadServiceImpl){
        this.uploadServiceImpl = uploadServiceImpl;
    }

    /**
     *检测表名是否可用
     * @param tableName
     * @param request
     * @return {"isAvailable":"false/true"}
     */
    @ResponseBody
    @RequestMapping(value = "/checkTableName")
    public String checkTableName(@RequestBody String tableName, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(tableName);
        String result = uploadServiceImpl.checkTableName(user.getId()+"_"+tableName);
        return result;
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @return {"uploadResult:"false/true"}
     */
    @ResponseBody
    @RequestMapping(value = "/upload")
    public String doUploadReadWrite(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request){
        String result = uploadServiceImpl.upload(file,request);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/readAndWrite")
    public String readAneWrite( HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        try {
            uploadServiceImpl.fileRead(uploadServiceImpl.getFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        uploadServiceImpl.fileWrite(user.getId(),uploadServiceImpl.getTableName(),uploadServiceImpl.getReadResultList());

        if (uploadServiceImpl.getErrorCode() == 1){
            return "{\"isFinish\":\"true\"";
        }

        return "{\"isFinish\":\"false\"";
    }


    /**
     * 获取状态信息
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value = "/getUploadStatus")
    public String getUploadStatus(){
        return uploadServiceImpl.getUploadStatus();
    }*/

}
