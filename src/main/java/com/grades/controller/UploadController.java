package com.grades.controller;


import com.grades.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/GradesSystem")
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/upload.do")
    public ModelAndView doUpload(@RequestParam("uploadFile") CommonsMultipartFile file, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String result = uploadService.upload(file,request);
        mav.addObject("error",result);
        mav.setViewName("error");
        return mav;
    }
}
