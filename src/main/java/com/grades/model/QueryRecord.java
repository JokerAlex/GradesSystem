package com.grades.model;

import java.util.List;

public class QueryRecord {
    private int queryRecordId;
    private String queryIdName;
    private List<TableInfo> tableInfo;

    public QueryRecord() {
    }

    public QueryRecord(int queryRecordId, String queryIdName, List<TableInfo> tableInfo) {
        this.queryRecordId = queryRecordId;
        this.queryIdName = queryIdName;
        this.tableInfo = tableInfo;
    }

    public int getQueryRecordId() {
        return queryRecordId;
    }

    public void setQueryRecordId(int queryRecordId) {
        this.queryRecordId = queryRecordId;
    }

    public String getQueryIdName() {
        return queryIdName;
    }

    public void setQueryIdName(String queryIdName) {
        this.queryIdName = queryIdName;
    }

    public List<TableInfo> getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(List<TableInfo> tableInfo) {
        this.tableInfo = tableInfo;
    }
}
