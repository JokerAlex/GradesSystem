package com.grades.serviceImpl;

import com.grades.mapping.UserMapper;
import com.grades.model.User;
import com.grades.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaintainServiceImpl implements MaintainService {

    private UserMapper userMapper;

    @Autowired
    public MaintainServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

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
}
