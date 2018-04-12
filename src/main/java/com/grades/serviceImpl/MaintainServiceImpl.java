package com.grades.serviceImpl;

import com.grades.mapping.CollegeMapper;
import com.grades.mapping.GradeMapper;
import com.grades.mapping.TableInfoMapper;
import com.grades.mapping.UserMapper;
import com.grades.model.College;
import com.grades.model.Grade;
import com.grades.model.TableInfo;
import com.grades.model.User;
import com.grades.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaintainServiceImpl implements MaintainService {

    private UserMapper userMapper;
    private CollegeMapper collegeMapper;
    private TableInfoMapper tableInfoMapper;
    private GradeMapper gradeMapper;

    @Autowired
    public MaintainServiceImpl(UserMapper userMapper, CollegeMapper collegeMapper, TableInfoMapper tableInfoMapper, GradeMapper gradeMapper){
        this.userMapper = userMapper;
        this.collegeMapper = collegeMapper;
        this.tableInfoMapper = tableInfoMapper;
        this.gradeMapper = gradeMapper;
    }

    //修改密码
    public String changPwd(String userId, String oldPwd, String newPwd) {
        if ((userId != null && !userId.equals(""))
                && (oldPwd != null && !oldPwd.equals(""))
                && (newPwd != null && !newPwd.equals(""))){
            String uid = userMapper.checkPwd(Integer.valueOf(userId),oldPwd);
            if (uid == null)
                return "{\"oldPwd\":\"false\",\"changeResult\":\"false\"}";
            boolean flag = userMapper.changPwd(newPwd,Integer.valueOf(userId));
            return "{\"oldPwd\":\"false\",\"changeResult\":\""+flag+"\"}";
        }
        return "{\"oldPwd\":\"false\",\"changeResult\":\"false\"}";
    }

    //用户信息更新
    public boolean updateUserInfo(User user) {
        if (user == null){
            return false;
        }
        User temp = new User();
        temp.setId(user.getId());
        temp.setName(user.getName());
        temp.setCollegeId(user.getCollegeId());
        temp.setGrade(user.getGrade());
        boolean flag = userMapper.updateUserInfo(temp);
        return flag;
    }

    //获取所有学院信息
    public List<College> getAllColleges() {
        return collegeMapper.getAllColleges();
    }

    //插入学院信息
    public int insertCollege(List<College> collegeList) {
        if (collegeList.size() != 0){
            return collegeMapper.insertCollege(collegeList);
        }
        return 0;
    }

    //学院信息删除
    public String delCollege(String collegeId) {
        boolean delResult = false;
        if (collegeId !=null && !collegeId.trim().equals("")){
            delResult = collegeMapper.delCollege(Integer.valueOf(collegeId));
        }
        return "{\"delResult\":\""+delResult+"\"}";
    }

    //学院信息更新
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

    //获取所有年级信息
    public List<Grade> getAllGrades() {
        return gradeMapper.getAllGrades();
    }

    //插入年级信息
    public int insertGrade(List<Grade> gradeList) {
        if (gradeList.size() != 0){
            return gradeMapper.insertGrade(gradeList);
        }
        return 0;
    }

    //获取用户上传的所有表
    public List<TableInfo> getAllTables(String userId, String userGrade, String tableName) {
        if (userId != null && !userId.trim().equals("")){
            if (userGrade == null){
                userGrade = "";
            }
            if (tableName == null){
                tableName = "";
            }
            return tableInfoMapper.searchTables(Integer.valueOf(userId),userGrade,tableName);
        }
        return null;
    }
}
