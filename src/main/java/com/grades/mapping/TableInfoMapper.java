package com.grades.mapping;

import com.grades.model.TableInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableInfoMapper {

    List<TableInfo> getAllTables(int userId);

    String findTable(String table_info_name);

    boolean delTable(int tableId);

    boolean insertTableInfo(TableInfo tableInfo);

    boolean updatePageViews(int tableId);

    boolean createTable(@Param("table_name") String table_name, @Param("list") List<String> list);

    boolean insertData(@Param("table_name") String table_name, @Param("list") List<List<String>> list);

    boolean insertDataOne(@Param("table_name") String table_name, @Param("list") List<String> list);
}
