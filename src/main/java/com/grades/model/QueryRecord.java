package com.grades.model;

import java.util.List;
import java.util.Map;

public class QueryRecord {
    private int queryRecordId;
    private String queryId;
    private Map tableStatus;

    public QueryRecord(int queryRecordId, String queryId, Map tableStatus) {
        this.queryRecordId = queryRecordId;
        this.queryId = queryId;
        this.tableStatus = tableStatus;
    }

    public int getQueryRecordId() {
        return queryRecordId;
    }

    public void setQueryRecordId(int queryRecordId) {
        this.queryRecordId = queryRecordId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public Map getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(Map tableStatus) {
        this.tableStatus = tableStatus;
    }
}
