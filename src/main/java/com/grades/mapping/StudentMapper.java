package com.grades.mapping;

import com.grades.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    Student getStu(@Param("stuId") String stuId, @Param("stuName") String stuName);

    List<Student> getAllStu(@Param("grade") String grade);

    int insertStuBatch(@Param("stuList") List<Student> stuList);

    int insertStu(Student stu);

    int updateStu(Student stu);
}
