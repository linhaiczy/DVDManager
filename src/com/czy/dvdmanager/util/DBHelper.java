package com.czy.dvdmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class DBHelper {
    /*

     */
    //访问属性文件
    private static Properties prop;
    //创建线程安全的List集合
    private static List<Connection> cons = Collections.synchronizedList(new ArrayList<>());

    static {
        prop = new Properties();

        try {
            //加载配置文件
            prop.load(DBHelper.class.getClassLoader()/*类加载器默认位置src*/.getResourceAsStream("config.properties"));
            //加载驱动
            Class.forName(prop.getProperty("CLASS_DRIVER"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建连接
    private static Connection creatConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(prop.getProperty("URL"),prop.getProperty("USERNAME"),prop.getProperty("PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //获取连接
    public static synchronized Connection getConnection(){
        Connection conn = null;
        if (cons.isEmpty()){
            conn = creatConnection();
        }else {
            conn = cons.remove(cons.size()-1);
        }
        return conn;
    }

    //关闭连接
    public static void closeConnection(Connection conn){

        if (cons.size() < Integer.parseInt(prop.getProperty("MAX_SIZE"))){
            cons.add(conn);
        }else {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            Connection conn = getConnection();

            closeConnection(conn);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
}
