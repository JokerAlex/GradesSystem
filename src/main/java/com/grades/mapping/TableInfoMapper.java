package com.grades.mapping;

import com.grades.model.TableInfo;

import java.util.List;

public interface TableInfoMapper {

    List<TableInfo> getAllTables(int userId);

    boolean delTable(int tableId);

    boolean insertTableInfo(TableInfo tableInfo);

    boolean updatePageViews(int tableId);
}
