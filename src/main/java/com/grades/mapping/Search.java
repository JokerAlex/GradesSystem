package com.grades.mapping;

import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;


public interface Search {

    List<String> getColNames(@Param("tableName") String tableName);

    List<LinkedHashMap<String,String>> getAll(@Param("list") List<String> colNames,
                                    @Param("tableName") String tableName);

    LinkedHashMap<String,String> getOne(@Param("list") List<String> colNames,
                                            @Param("tableName") String tableName,
                                            @Param("stuIdName") String stuIdName,
                                            @Param("id") String id,
                                            @Param("stuName") String Name,
                                            @Param("name") String name);
}

