package com.grades.service;

import com.grades.model.*;

import java.util.List;

public interface MaintainService {

    String changPwd(User user, String oldPwd, String newPwd);

    String updateUserInfo(User user);

    List<College> getAllColleges();

    int insertCollege(List<College> collegeList);

    String delCollege(String collegeId);

    String updateCollege(String collegeId, String collegeName, String collegeIdOld);

    List<Grade> getAllGrades();

    int insertGrade(List<Grade> gradeList);

    List<TableInfo> getAllTables(User user, String userGrade, String tableName);

    String delTable(TableInfo tableInfo);

    List<QueryRecord> getQueryRecords(int userId);

    String insertNewRecord(String[] tableIds,int userId);

    String delRecord(String queryId,int userId);
}
