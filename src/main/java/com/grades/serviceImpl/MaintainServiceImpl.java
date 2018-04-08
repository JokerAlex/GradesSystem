package com.grades.serviceImpl;

import com.grades.mapping.CollegeMapper;
import com.grades.mapping.UserMapper;
import com.grades.model.User;
import com.grades.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaintainServiceImpl implements MaintainService {

    private UserMapper userMapper;
    private CollegeMapper collegeMapper;

    @Autowired
    public MaintainServiceImpl(UserMapper userMapper, CollegeMapper collegeMapper){
        this.userMapper = userMapper;
        this.collegeMapper = collegeMapper;
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
}
