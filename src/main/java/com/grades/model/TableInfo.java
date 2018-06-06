package com.grades.model;

public class TableInfo {
    private int tableId;
    private String tableName;
    private int userId;
    private String userTrueName;
    private int pageViews;
    private String tableStatus;

    public TableInfo() {
    }

    public TableInfo(String tableName, int userId) {
        this.tableName = tableName;
        this.userId = userId;
    }

    public TableInfo(int tableId, String tableName, int userId, String userTrueName, int pageViews, String tableStatus) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.userId = userId;
        this.userTrueName = userTrueName;
        this.pageViews = pageViews;
        this.tableStatus = tableStatus;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}
