package com.czy.dvdmanager.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class JDBCUtil {
    private static Properties prop;
    private static List<Connection> connList = Collections.synchronizedList(new ArrayList<>());

    static {
        try {
            prop.load(JDBCUtil.class.getClassLoader().getResourceAsStream("config.properties"));
            Class.forName(prop.getProperty("CLASS_NAME"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //创建连接
    public static Connection creatConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(prop.getProperty("URL"),prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //获取连接
    public static synchronized Connection getConnection(){
        Connection conn = null;
        if (connList.isEmpty()){
            conn = creatConnection();
        }else {
            conn = connList.remove(connList.size() - 1);
        }
        return conn;
    }

    public static void close(Connection conn){
        if (connList.size() < Integer.parseInt(prop.getProperty("MAX_SIZE"))){
            connList.add(conn);
        }else {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }
}
