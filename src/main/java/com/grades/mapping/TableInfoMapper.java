package com.grades.mapping;

import com.grades.model.TableInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableInfoMapper {

    List<TableInfo> getAllTablesByUser(int userId);

    List<TableInfo> getAllTablesByGrade(String userGrade);

    List<TableInfo> searchTables(@Param("userId") int userId,@Param("userGrade") String userGrade,@Param("userCollege") int userCollege, @Param("tableName") String tableName);

    String findTable(String table_info_name);

    int delTable(List tableId);

    int dropTable(@Param("tableName") String tableName);

    boolean insertTableInfo(TableInfo tableInfo);

    boolean updatePageViews(String tableName);

    boolean createTable(@Param("table_name") String table_name, @Param("list") List<String> list);

    boolean insertData(@Param("table_name") String table_name, @Param("list") List<List<String>> list);

    boolean insertDataOne(@Param("table_name") String table_name, @Param("list") List<String> list);
}
