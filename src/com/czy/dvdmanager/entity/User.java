package com.czy.dvdmanager.entity;

public class User {
    private int id;
    private String userName;
    private String password;
    private long creatTime;
    private int status;
    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", creatTime=" + creatTime +
                ", status=" + status +
                '}';
    }

    public User() {
    }

    public User(int id, String userName, String password, long creatTime, int status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.creatTime = creatTime;
        this.status = status;
    }

    public User(int id, String userName, String password, long creatTime, int status, String a, String b) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.creatTime = creatTime;
        this.status = status;
        this.a = a;
        this.b = b;
    }
}
