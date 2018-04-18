package com.grades.model;

public class TableStatus {
    private int tableId;
    private String tableName;
    private String tableStatus;

    public TableStatus(String tableName, String tableStatus) {
        this.tableName = tableName;
        this.tableStatus = tableStatus;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}
