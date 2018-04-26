package com.grades.mapping;

import com.grades.model.QueryRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryIdMapper {

    //query_id表
    List<QueryRecord> getRecordIdAndTables(int userId);

    int[] getRecordId(@Param("queryIdName") String queryIdName, @Param("userId") int userId);

    boolean insertRecordId(@Param("queryIdName") String queryIdName, @Param("userId") int userId);

    boolean delQueryId(@Param("list") List list, @Param("userId") int userId);

    //query_tables表
    boolean insertIdAndTables(@Param("queryId") int queryId, @Param("list") List tableIdList, @Param("status") String status);

    boolean delIdAndTables(List queryId);

    boolean updateTableStatus(@Param("status") String status, @Param("tableId") int tableId);


}
