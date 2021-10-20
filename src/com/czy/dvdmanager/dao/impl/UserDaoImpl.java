package com.czy.dvdmanager.dao.impl;

import com.czy.dvdmanager.dao.UserDao;
import com.czy.dvdmanager.entity.User;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.util.DBHelper;

import java.rmi.dgc.DGC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDao;

    private UserDaoImpl(){

    }

    public static UserDao getInstance(){
        if (userDao == null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    @Override
    public User findById(int id) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_user where id=?";
        User user = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setCreatTime(rs.getLong("create_time"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_user;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setCreatTime(rs.getLong("create_time"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<User> findByStatus(State state) {
        List<User> list = new ArrayList<>();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_user where status=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,state.getValue());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setCreatTime(rs.getLong("create_time"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(User user, Connection conn) {
        String sql = "insert into dvd_user (username,password,create_time,status,a,b) values (?,?,?,?,default ,default );";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getPassword());
            stmt.setLong(3,user.getCreatTime());
            stmt.setInt(4,user.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getByName(String username){
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_user where username=?";
        User user = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setCreatTime(rs.getLong("create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return user;
    }

    @Override
    public void update(User user, Connection conn) {

        String sql = "update dvd_user set username=?,password=?,status=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getPassword());
            stmt.setInt(3,user.getStatus());
            stmt.setInt(4,user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id, Connection conn) {

        String sql = "update dvd_user set status=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,State.ISDEL.getValue());
            stmt.setInt(2,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
