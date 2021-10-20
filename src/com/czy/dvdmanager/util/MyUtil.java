package com.czy.dvdmanager.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;

public class MyUtil {
    private static Properties prop = new Properties();

    static {
        try {//静态代码块加载配置文件
            prop.load(MyUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getInstance(String key){
        String classPath = prop.getProperty(key);
        Object result = null;
        try {
            Class clazz = Class.forName(classPath);
            Method method = clazz.getMethod("getInstance");

            result = method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 格式化字符串
     * @param value long类型时间
     * @return 格式化后的字符串
     */
    public static String getDate(long value){
        Date date = new Date(value);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static long getDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long time = 0;
        try {
            time = format.parse(str).getTime();
        } catch (ParseException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return time;
    }

    public static long getOneDayBefore(String str){
        return getDate(str)-1000*60*60*24;
    }
    public static long getOneDayAfter(String str){
        return getDate(str)+1000*60*60*24;
    }
    public static long getOneDayAfter(long time){
        return time+1000*60*60*24;
    }

    public static int charge(long start){
        long l = System.currentTimeMillis() - start;
        return (int) (l/1000/60/60/24);
    }

    public static int charge(String start){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = date.getTime();
        return charge(l);
    }

    public static void main(String[] args) {
        System.out.println(getDate("2012-2-3"));

    }
}
