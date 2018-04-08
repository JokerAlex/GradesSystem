package com.grades.mapping;

import com.grades.model.College;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollegeMapper {

    List<College> getAllColleges();

    boolean delCollege(int collegeId);

    boolean updateCollege(@Param("collegeId") int collegeId,@Param("collegeName") String collegeName,@Param("collegeIdOld")int collegeIdOld);
}
