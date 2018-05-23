package com.grades.mapping;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


public interface Search {
    List<HashMap<String,String>> getAll(@Param("tableName") String tableName);

    List<HashMap<String,String>> getOne(@Param("tableName") String tableName,
                                        @Param("stuIdName") String stuIdName,
                                        @Param("id") String id,
                                        @Param("stuName") String Name,
                                        @Param("name") String name);
}

