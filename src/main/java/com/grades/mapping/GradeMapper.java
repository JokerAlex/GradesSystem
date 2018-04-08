package com.grades.mapping;

import com.grades.model.Grade;

import java.util.List;

public interface GradeMapper {

    List<Grade> getAllGrades();

    boolean insertGrade(String gradeId);
}
