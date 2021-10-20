package com.czy.dvdmanager.entity;

public class Lend {
    private int id;
    private int dvdId;
    private long lendTime;
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

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public long getLendTime() {
        return lendTime;
    }

    public void setLendTime(long lendTime) {
        this.lendTime = lendTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "id=" + id +
                ", dvdId=" + dvdId +
                ", lendTime=" + lendTime +
                ", status=" + status +
                '}';
    }

    public Lend() {
    }

    public Lend(int id, int dvdId, long lendTime, int status) {
        this.id = id;
        this.dvdId = dvdId;
        this.lendTime = lendTime;
        this.status = status;
    }

    public Lend(int id, int dvdId, long lendTime, int status, String a, String b) {
        this.id = id;
        this.dvdId = dvdId;
        this.lendTime = lendTime;
        this.status = status;
        this.a = a;
        this.b = b;
    }
}
