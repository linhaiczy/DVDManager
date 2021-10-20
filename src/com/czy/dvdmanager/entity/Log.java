package com.czy.dvdmanager.entity;

public class Log {
    private int id;
    private int userId;
    private int dvdId;
    private long operateTime;
    private String operateInfo;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(long operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateInfo() {
        return operateInfo;
    }

    public void setOperateInfo(String operateInfo) {
        this.operateInfo = operateInfo;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", userId=" + userId +
                ", dvdId=" + dvdId +
                ", operateTime=" + operateTime +
                ", operateInfo='" + operateInfo + '\'' +
                '}';
    }

    public Log() {
    }

    public Log(int id, int userId, int dvdId, long operateTime, String operateInfo) {
        this.id = id;
        this.userId = userId;
        this.dvdId = dvdId;
        this.operateTime = operateTime;
        this.operateInfo = operateInfo;
    }

    public Log(int id, int userId, int dvdId, long operateTime, String operateInfo, String a, String b) {
        this.id = id;
        this.userId = userId;
        this.dvdId = dvdId;
        this.operateTime = operateTime;
        this.operateInfo = operateInfo;
        this.a = a;
        this.b = b;
    }
}
