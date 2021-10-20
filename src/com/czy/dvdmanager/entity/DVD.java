package com.czy.dvdmanager.entity;

import sun.applet.Main;

import java.lang.reflect.Field;

public class DVD {
    private int id;
    private String name;
    private int status;
    private int lenCount;
    private double price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLenCount() {
        return lenCount;
    }

    public void setLenCount(int lenCount) {
        this.lenCount = lenCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", lenCount=" + lenCount +
                ", price=" + price +
                '}';
    }

    public DVD() {
    }

    public DVD(int id, String name, int status, int lenCount, double price) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.lenCount = lenCount;
        this.price = price;
    }

    public DVD(int id, String name, int status, int lenCount, double price, String a, String b) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.lenCount = lenCount;
        this.price = price;
        this.a = a;
        this.b = b;
    }

}
