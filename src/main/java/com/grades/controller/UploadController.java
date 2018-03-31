package com.grades.controller;


import com.grades.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    public String doUpload(@RequestParam("uploadFile")CommonsMultipartFile file, HttpServletRequest request){
        String result = uploadService.upload(file,request);
        return null;
    }
}
