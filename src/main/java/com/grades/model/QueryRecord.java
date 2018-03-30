package com.grades.model;

import java.util.List;

public class QueryRecord {
    private int queryRecordId;
    private List queryTableIds;
    private String queryId;
    private String tableStatus;

    public QueryRecord(int queryRecordId, List queryTableIds, String queryId, String tableStatus) {
        this.queryRecordId = queryRecordId;
        this.queryTableIds = queryTableIds;
        this.queryId = queryId;
        this.tableStatus = tableStatus;
    }

    public int getQueryRecordId() {
        return queryRecordId;
    }

    public void setQueryRecordId(int queryRecordId) {
        this.queryRecordId = queryRecordId;
    }

    public List getQueryTableId() {
        return queryTableIds;
    }

    public void setQueryTableId(List queryTableIds) {
        this.queryTableIds = queryTableIds;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}
