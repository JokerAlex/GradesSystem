package com.grades.controller;


import com.alibaba.fastjson.JSONObject;
import com.grades.model.*;
import com.grades.service.MaintainService;
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
     * @param
     * @param searchMap(userGrade,tableName)
     * @return List<TableInfo>
     */
    @ResponseBody
    @RequestMapping(value = "/getTables")
    public List<TableInfo> getTables(@RequestBody Map<String,String> searchMap, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        System.out.println(user.getId()+user.getUserName()+searchMap.get("userGrade")+searchMap.get("tableName"));
        return maintainService.getAllTables(user, searchMap.get("userGrade"), searchMap.get("tableName"));
    }


    /**
     * 删除表
     * @param tableInfo
     * @return {"delResult":"true/false","dropResult":"true/false"}
     */
    @ResponseBody
    @RequestMapping(value = "/delTables")
    public String delTable(@RequestBody List<TableInfo> tableInfo){
        return maintainService.delTable(tableInfo);
    }


    /**
     * 已上传文件列表页面end
     */

    /**
     * 发布记录页面
     */

    /**
     * 获取发布记录
     * @param request
     * @return List<QueryRecord>
     */
    @ResponseBody
    @RequestMapping(value = "/getRecords")
    public List<QueryRecord> getRecords(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return maintainService.getQueryRecords(user.getId());
    }

    /**required = false,
     * @RequestParam(required = false,value = "tableIds[]")
     * 生成新的发布记录
     * @param tableNames
     * @param request
     * @return {"insertRecordResult":"true/false","insertTableResult":"true/false"}
     */
    @ResponseBody
    @RequestMapping(value = "/insertRecord")
    public JSONObject insertRecord(@RequestParam(value = "tableNames[]") String[] tableNames, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        JSONObject jsonObject = maintainService.insertNewRecord(tableNames,user.getId());
        return jsonObject;
    }

    /**
     * 删除发布记录
     * @param queryId
     * @param request
     * @return {"isDel":"true/false"}
     */
    @ResponseBody
    @RequestMapping(value = "/delRecords")
    public String delRecords(@RequestBody String queryId,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return maintainService.delRecord(queryId,user.getId());
    }

    /**
     * 发布记录页面end
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
        String sessionId = GetUser.getUser(request.getCookies());
        System.out.println("changePerInfo------------->sessionId:"+sessionId);
        User userTemp = (User)request.getSession().getAttribute(sessionId);
        user.setId(userTemp.getId());
        System.out.println("userTemp----->"+userTemp.toString());
        System.out.println("user--------->"+user.toString());
        request.getSession().setAttribute(sessionId,user);
        return maintainService.updateUserInfo(user);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    public JSONObject getUserInfo(HttpServletRequest request){
        String sessionId = GetUser.getUser(request.getCookies());
        System.out.println("getUserInfo---------->sessionId:"+sessionId);
        User user = (User) request.getSession().getAttribute(sessionId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo",user);
        return jsonObject;
    }

    /**
     * 个人信息修改界面end
     */


}
