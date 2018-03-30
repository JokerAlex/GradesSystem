package com.grades.model;

public class TableInfo {
    private int id;
    private String name;
    private int userId;
    private int pageViews;

    public TableInfo(int id, String name, int userid, int pageViews) {
        this.id = id;
        this.name = name;
        this.userId = userid;
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

    public void setUserid(int userid) {
        this.userId = userid;
    }

    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }
}
