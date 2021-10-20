package com.czy.dvdmanager.dao.impl;

import com.czy.dvdmanager.dao.LogDao;
import com.czy.dvdmanager.entity.Lend;
import com.czy.dvdmanager.entity.Log;
import com.czy.dvdmanager.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LogDaoImpl implements LogDao {
    private static LogDaoImpl logDao;

    private LogDaoImpl(){

    }

    public static LogDao getInstance(){
        if (logDao == null) {
            logDao = new LogDaoImpl();
        }
        return logDao;
    }

    @Override
    public void add(Log log, Connection conn) {
        String sql = "insert into dvd_log(user_id,dvd_id,operate_time,operate_info,a,b) values (?,?,?,?,default ,default )";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, log.getUserId());
            stmt.setInt(2,log.getDvdId());
            stmt.setLong(3,log.getOperateTime());
            stmt.setString(4,log.getOperateInfo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delById(int id, Connection conn) {

        String sql = "delete from dvd_log where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Log log, Connection conn) {

        String sql = "update dvd_log set user_id=?,dvd_id=?,operate_time=?,operate_info=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, log.getUserId());
            stmt.setInt(2,log.getDvdId());
            stmt.setLong(3,log.getOperateTime());
            stmt.setString(4,log.getOperateInfo());
            stmt.setInt(5,log.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Log getById(int id) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_log where id= ?";
        Log log = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                log = new Log();
                log.setUserId(rs.getInt("user_id"));
                log.setOperateTime(rs.getLong("operate_time"));
                log.setOperateInfo(rs.getString("operate_info"));
                log.setDvdId(rs.getInt("dvd_id"));
                log.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return log;
    }

    @Override
    public List<Log> listAll() {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_log";
        List<Log> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Log log = new Log();
                log.setUserId(rs.getInt("user_id"));
                log.setOperateTime(rs.getLong("operate_time"));
                log.setOperateInfo(rs.getString("operate_info"));
                log.setDvdId(rs.getInt("dvd_id"));
                log.setId(rs.getInt("id"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Log> listByUserId(int userId) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_log where user_id=?";
        List<Log> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Log log = new Log();
                log.setUserId(rs.getInt("user_id"));
                log.setOperateTime(rs.getLong("operate_time"));
                log.setOperateInfo(rs.getString("operate_info"));
                log.setDvdId(rs.getInt("dvd_id"));
                log.setId(rs.getInt("id"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Log> listByDvdId(int dvdId) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_log where dvd_id=?";
        List<Log> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,dvdId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Log log = new Log();
                log.setUserId(rs.getInt("user_id"));
                log.setOperateTime(rs.getLong("operate_time"));
                log.setOperateInfo(rs.getString("operate_info"));
                log.setDvdId(rs.getInt("dvd_id"));
                log.setId(rs.getInt("id"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Log> listByOperateInfo(String info) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_log where operate_info like ?";
        List<Log> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,"%"+info+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Log log = new Log();
                log.setUserId(rs.getInt("user_id"));
                log.setOperateTime(rs.getLong("operate_time"));
                log.setOperateInfo(rs.getString("operate_info"));
                log.setDvdId(rs.getInt("dvd_id"));
                log.setId(rs.getInt("id"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Log> listUserLendLog(int userId) {
        Connection conn = DBHelper.getConnection();
        String sql = "select l.* from dvd_log as l inner join dvd_dvd as d on l.dvd_id=d.id where l.user_id=? and d.status=2 and l.operate_info='借阅'";

        List<Log> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Log log = new Log();
                log.setUserId(rs.getInt("user_id"));
                log.setOperateTime(rs.getLong("operate_time"));
                log.setOperateInfo(rs.getString("operate_info"));
                log.setDvdId(rs.getInt("dvd_id"));
                log.setId(rs.getInt("id"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
