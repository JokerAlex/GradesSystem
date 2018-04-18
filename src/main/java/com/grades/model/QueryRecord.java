package com.grades.model;

import java.util.List;

public class QueryRecord {
    private int queryRecordId;
    private String queryIdName;
    private List<TableStatus> tableStatus;

    public QueryRecord() {
    }

    public QueryRecord(int queryRecordId, String queryIdName, List<TableStatus> tableStatus) {
        this.queryRecordId = queryRecordId;
        this.queryIdName = queryIdName;
        this.tableStatus = tableStatus;
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

    public List<TableStatus> getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(List<TableStatus> tableStatus) {
        this.tableStatus = tableStatus;
    }
}
