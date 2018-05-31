package com.grades.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.grades.mapping.*;
import com.grades.model.*;
import com.grades.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class MaintainServiceImpl implements MaintainService {

    private UserMapper userMapper;
    private CollegeMapper collegeMapper;
    private TableInfoMapper tableInfoMapper;
    private GradeMapper gradeMapper;
    private QueryIdMapper queryIdMapper;

    @Autowired
    public MaintainServiceImpl(UserMapper userMapper, CollegeMapper collegeMapper, TableInfoMapper tableInfoMapper, GradeMapper gradeMapper, QueryIdMapper queryIdMapper){
        this.userMapper = userMapper;
        this.collegeMapper = collegeMapper;
        this.tableInfoMapper = tableInfoMapper;
        this.gradeMapper = gradeMapper;
        this.queryIdMapper = queryIdMapper;
    }

    /**
     * 修改密码，新密码不能与原密码相同
     * @param user
     * @param oldPwd
     * @param newPwd
     * @return {"oldPwd":"false/true","isPwdSame":"true/false","changeResult":"false/true"}
     */
    public String changPwd(User user, String oldPwd, String newPwd) {
        if ((oldPwd != null && !oldPwd.equals(""))
                && (newPwd != null && !newPwd.equals(""))){
            String uid = userMapper.checkPwd(user.getId(),oldPwd);
            if (uid == null){
                return "{\"oldPwd\":\"false\",\"isPwdSame\":\"true\",\"changeResult\":\"false\"}";
            }
            if (oldPwd.equals(newPwd)){
                return "{\"oldPwd\":\"true\",\"isPwdSame\":\"true\",\"changeResult\":\"false\"}";
            }
            boolean flag = userMapper.changPwd(newPwd,user.getId());
            return "{\"oldPwd\":\"false\",\"isPwdSame\":\"false\",\"changeResult\":\""+flag+"\"}";
        }
        return "{\"oldPwd\":\"false\",\"changeResult\":\"false\"}";
    }

    /**
     * 用户信息更新
     * @param user
     * @return {"updateResult":"true/false"}
     */
    public String updateUserInfo(User user) {
        if (user == null){
            return "{\"updateResult\":\"false\"}";
        }
        boolean flag = userMapper.updateUserInfo(user);
        return "{\"updateResult\":\""+flag+"\"}";
    }

    /**
     * 获取所有学院信息
     * @return List<College>
     */
    public List<College> getAllColleges() {
        return collegeMapper.getAllColleges();
    }

    /**
     * 插入学院信息
     * @param collegeList
     * @return int（数据条数）
     */
    public int insertCollege(List<College> collegeList) {
        if (collegeList.size() != 0){
            return collegeMapper.insertCollege(collegeList);
        }
        return 0;
    }

    /**
     * 学院信息删除
     * @param collegeId
     * @return {"delResult":"false/true"}
     */
    public String delCollege(String collegeId) {
        boolean delResult = false;
        if (collegeId !=null && !collegeId.trim().equals("")){
            delResult = collegeMapper.delCollege(Integer.valueOf(collegeId));
        }
        return "{\"delResult\":\""+delResult+"\"}";
    }

    /**
     * 学院信息更新
     * @param collegeId
     * @param collegeName
     * @param collegeIdOld
     * @return {"updateResult":"true/false"}
     */
    public String updateCollege(String collegeId, String collegeName, String collegeIdOld) {
        boolean isCollegeId = collegeId != null && !collegeId.trim().equals("");
        boolean isCollegeName = collegeName != null && !collegeName.trim().equals("");
        boolean updateResult = false;
        if (isCollegeId || isCollegeName) {
            int id = Integer.valueOf(collegeId);
            int idOld = Integer.valueOf(collegeIdOld);
            updateResult = collegeMapper.updateCollege(id,collegeName,idOld);
        }
        return "{\"updateResult\":\""+updateResult+"\"}";
    }

    /**
     * 获取所有年级信息
     * @return List<Grade>
     */
    public List<Grade> getAllGrades() {
        return gradeMapper.getAllGrades();
    }

    /**
     * 插入年级信息
     * @param gradeList
     * @return int(插入数据条数）
     */
    public int insertGrade(List<Grade> gradeList) {
        if (gradeList.size() != 0){
            return gradeMapper.insertGrade(gradeList);
        }
        return 0;
    }

    /**
     * 获取用户上传的所有表(模糊查询)
     * @param user
     * @param userGrade
     * @param tableName
     * @return List<TableInfo>
     */
    public List<TableInfo> getAllTables(User user, String userGrade, String tableName) {
        String grade;
        if (userGrade.equals("false")){
            grade = "";
        }else {
            grade = user.getGrade();
        }
        if (tableName == null || tableName.trim().equals("")){
            tableName = "";
        }
        return tableInfoMapper.searchTables(user.getId(),grade,user.getCollegeId(),tableName);
    }

    /**
     * 删除表及其信息
     * @param tableInfos
     * @return {"delResult":"true/false","dropResult":"true/false"}
     */
    public String delTable(List<TableInfo> tableInfos){
        boolean delResult = false;
        boolean dropResult = false;
        if (tableInfos.size() != 0){
            delResult = tableInfoMapper.delTable(tableInfos);
            for (int i=0 ;i<tableInfos.size();i++){
                dropResult = tableInfoMapper.dropTable(tableInfos.get(i).getName());
                System.out.println("dropResult-------->"+dropResult);
                queryIdMapper.updateTableStatus("该表已删除",tableInfos.get(i).getName());
            }

        }
        return "{\"delResult\":\""+delResult+"\",\"dropResult\":\""+dropResult+"\"}";
    }

    /**
     * 获取用户所有发布记录
     * @param userId
     * @return List<QueryRecord>
     */
    public List<QueryRecord> getQueryRecords(int userId){
        return queryIdMapper.getRecordIdAndTables(userId);
    }

    /**
     * 生成新的发布记录
     * @param tableNames
     * @param userId
     * @return {"insertRecordResult":"true/false","insertTableResult":"true/false"}
     */
    public JSONObject insertNewRecord(String[] tableNames, int userId){
        //用时间作为发布记录的名称
        String queryName = new Date().toString().replace(" ","")+(int)(Math.random()*100);
        boolean insertRecordResult = queryIdMapper.insertRecordId(queryName,userId);
        boolean insertTableResult = false;
        int insertCode = -1;
        String inserResult = "";
        int[] queryId = queryIdMapper.getRecordId(queryName,userId);
        if (insertRecordResult){
            List tableList = new ArrayList();
            for (String tableName:tableNames){
                tableList.add(tableName);
            }
            insertTableResult = queryIdMapper.insertIdAndTables(queryId[0],tableList,"该表可查询");
            if (insertTableResult){

            }
        }
        if (insertRecordResult && insertTableResult){
            insertCode = 0;
            inserResult = "记录生成成功";
        } else {
            insertCode = -1;
            inserResult = "记录生错误";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("insertCode",insertCode);
        jsonObject.put("insertResult",inserResult);
        jsonObject.put("address",queryName);
        return jsonObject;
    }

    /**
     * 删除发布记录
     * @param queryId
     * @param userId
     * @return {"isDel":"true/false"}
     */
    public String delRecord(String queryId,int userId){
        boolean isDel = false;
        List queryIds = new ArrayList();
        if (queryId != null){//删除选中记录
            queryIds.add(Integer.valueOf(queryId));
            boolean isTableRecordDel = queryIdMapper.delIdAndTables(queryIds);
            boolean isRecordDel = queryIdMapper.delQueryId(queryIds,userId);
            if (isTableRecordDel && isRecordDel){
                isDel = true;
            }
        } else {//删除全部记录
           int[] queryIdMapperRecordId = queryIdMapper.getRecordId(null,userId);
           for (int i : queryIdMapperRecordId){
               queryIds.add(i);
           }
           boolean isTableRecordDel = queryIdMapper.delIdAndTables(queryIds);
           boolean isRecordDel = queryIdMapper.delQueryId(queryIds,userId);
           if (isTableRecordDel && isRecordDel){
               isDel = true;
           }
        }
        return "{\"isDel\":\""+isDel+"\"}";
    }
}
