package com.grades.controller;


import com.grades.model.*;
import com.grades.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping(value = "/GradesSystem")
public class MaintainController {
    private MaintainService maintainService;
    @Autowired
    public MaintainController(MaintainService maintainService){
        this.maintainService = maintainService;
    }

    /**
     * 已上传文件列表页面
     */

    /**
     * 获取所有表，模糊查询
     * @param request
     * @param userGrade (true/false)
     * @param tableName
     * @return List<TableInfo>
     */
    @ResponseBody
    @RequestMapping(value = "/getTables")
    public List<TableInfo> getTables(HttpServletRequest request, String userGrade, String tableName){
        User user = (User)request.getSession().getAttribute("user");
        return maintainService.getAllTables(user,userGrade,tableName);
    }

    /**
     * 删除表
     * @param tableInfo
     * @return {"delResult":"true/false","dropResult":"true/false"}
     */
    @ResponseBody
    @RequestMapping(value = "delTable")
    public String delTable(@RequestBody TableInfo tableInfo){
        return maintainService.delTable(tableInfo);
    }


    /**
     * 已上传文件列表页面end
     */


    /**
     * 密码修改界面
     */

    /**
     * 修改密码
     * @param request
     * @param oldPwd
     * @param newPwd
     * @return {"oldPwd":"false/true","isPwdSame":"true/false","changeResult":"false/true"}
     */
    @ResponseBody
    @RequestMapping(value = "/changPwd")
    public String changPwd(HttpServletRequest request, String oldPwd, String newPwd){
        User user = (User)request.getSession().getAttribute("user");
        return maintainService.changPwd(user, oldPwd, newPwd);
    }

    /**
     * 密码修改界面end
     */

    /**
     * 个人信息修改界面
     */

    /**
     * 个人信息修改
     * @param request
     * @param user
     * @return {"updateResult":"true/false"}
     */
    @ResponseBody
    @RequestMapping(value = "/changePerInfo")
    public String updateUserInfo(HttpServletRequest request,@RequestBody User user){
        User userTemp = (User)request.getSession().getAttribute("user");
        user.setId(userTemp.getId());
        return maintainService.updateUserInfo(user);
    }

    /**
     * 个人信息修改界面end
     */


}
