package com.grades.model;

public class TableInfo {
    private int id;
    private String name;
    private int userId;
    private int pageViews;

    public TableInfo() {
    }

    public TableInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TableInfo(int id, String name, int userId, int pageViews) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.pageViews = pageViews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userId;
    }

    public void setUserid(int userId) {
        this.userId = userId;
    }

    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }
}
