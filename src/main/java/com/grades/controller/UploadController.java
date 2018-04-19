package com.grades.controller;


import com.grades.model.*;
import com.grades.serviceImpl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({"user"})
@RequestMapping(value = "/GradesSystem")
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
    public String checkTableName(String tableName,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
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
    public String doUpload(@RequestParam("uploadFile") CommonsMultipartFile file, HttpServletRequest request){
        String result = uploadServiceImpl.upload(file,request);
        return result;
    }

    /**
     * 读取文件内容
     * @return {"readResult":"false/true"}
     */
    @ResponseBody
    @RequestMapping(value = "fileRead")
    public String readExcel(){
        String result = null;
        try {
            result = uploadServiceImpl.fileRead(uploadServiceImpl.getFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取进度
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/readProgress")
    public float getReadProgress(){
        return uploadServiceImpl.getReadProgress();
    }

    /**
     * 将文件内容写入数据库
     * @param request
     * @return "{"createResult":"false/true","insertResult":"false/true","updateTableInfoResult":"false/true"}
     */
    @ResponseBody
    @RequestMapping(value = "fileWrite")
    public String writeToDb(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        String result = uploadServiceImpl.fileWrite(user.getId(),uploadServiceImpl.getTableName(),uploadServiceImpl.getReadResultList());
        return result;
    }

    /**
     * 写入进度
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "writeProgress")
    public float getWriteProgress(){
        return uploadServiceImpl.getWriteProgress();
    }

}
