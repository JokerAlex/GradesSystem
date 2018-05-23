package com.grades.mapping;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


public interface Search {

    List<String> getColNames(@Param("tableName") String tableName);

    List<Map<String,String>> getAll(@Param("list") List<String> colNames,
                                    @Param("tableName") String tableName);

    List<Map<String,String>> getOne(@Param("list") List<String> colNames,
                                        @Param("tableName") String tableName,
                                        @Param("stuIdName") String stuIdName,
                                        @Param("id") String id,
                                        @Param("stuName") String Name,
                                        @Param("name") String name);
}

