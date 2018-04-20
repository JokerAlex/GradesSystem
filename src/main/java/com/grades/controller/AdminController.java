package com.grades.controller;

import com.grades.model.*;
import com.grades.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping(value = "/Admin")
public class AdminController {
    private MaintainService maintainService;
    @Autowired
    public AdminController(MaintainService maintainService){
        this.maintainService = maintainService;
    }

    /**
     * 获取学院信息
     * @return List<College>
     */
    @ResponseBody
    @RequestMapping(value = "/getColleges")
    public List<College> getColleges(){
        return maintainService.getAllColleges();
    }

    /**
     * 删除学院
     * @param collegeId
     * @return {"delResult":"false/true"}
     */
    @ResponseBody
    @RequestMapping(value = "/delCollege")
    public String delCollege(String collegeId){
        return maintainService.delCollege(collegeId);
    }

    @ResponseBody
    @RequestMapping(value = "/insertCollege")
    public String insertCollge(){
        return null;
    }
}
