package com.grades.service;

import com.grades.model.*;

import java.util.List;

public interface MaintainService {

    String changPwd(String userId, String oldPwd, String newPwd);

    boolean updateUserInfo(User user);

    List<College> getAllColleges();

    int insertCollege(List<College> collegeList);

    String delCollege(String collegeId);

    String updateCollege(String collegeId, String collegeName, String collegeIdOld);

    List<Grade> getAllGrades();

    int insertGrade(List<Grade> gradeList);

    List<TableInfo> getAllTables(String userId, String userGrade, String tableName);
}
