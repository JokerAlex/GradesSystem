package com.grades.model;

public class User {
    private int id;
    private String userName;
    private String name;
    private String passWd;
    private String email;
    private int collegeId;
    private String college;
    private String grade;
    private String loginTime;
    private int identity;

    public User(){}

    /**
     *登录使用
     * @Param: [id, username, name, email, collegeId, college, grade, loginTime]
     * @return:
     * @Author: Alex
     * @Date:
    */
    public User(int id, String username, String name, String email,int collegeId, String college, String grade, String loginTime, int identity) {
        this.id = id;
        this.userName = username;
        this.name = name;
        this.email = email;
        this.collegeId = collegeId;
        this.college = college;
        this.grade = grade;
        this.loginTime = loginTime;
        this.identity = identity;
    }

    /**
     *修改信息使用
     * @Param: [id, username, name, email, college, grade, loginTime]
     * @return:
     * @Author: Alex
     * @Date:
     */
    public User(int id, String username, String name, String email, int collegeId, String grade, String loginTime) {
        this.id = id;
        this.userName = username;
        this.name = name;
        this.email = email;
        this.collegeId = collegeId;
        this.grade = grade;
        this.loginTime = loginTime;
    }
    /**
     *注册使用
     * @Param: [id, username, name, email, college, grade, loginTime]
     * @return:
     * @Author: Alex
     * @Date:
     */
    public User(String userName, String name, String passWd, String email, int collegeId, String grade,int identity) {
        this.userName = userName;
        this.name = name;
        this.passWd = passWd;
        this.email = email;
        this.collegeId = collegeId;
        this.grade = grade;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", passWd='" + passWd + '\'' +
                ", email='" + email + '\'' +
                ", collegeId=" + collegeId +
                ", college='" + college + '\'' +
                ", grade='" + grade + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", identity=" + identity +
                '}';
    }
}
