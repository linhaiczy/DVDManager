package com.czy.dvdmanager.dao.impl;

import com.czy.dvdmanager.dao.DVDDao;
import com.czy.dvdmanager.entity.DVD;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DVDDaoImpl implements DVDDao {

    private static DVDDaoImpl DVDDao;

    private DVDDaoImpl() {
    }

    public static DVDDao getInstance(){
        if (DVDDao == null){
            DVDDao = new DVDDaoImpl();
        }
        return DVDDao;
    }
    @Override
    public DVD add(DVD dvd, Connection conn) {
        String sql = "insert into dvd_dvd(dvdname,status,len_count,price,a,b) values (?,?,?,?,default,default)";
        String querySql = "select * from dvd_dvd where dvdname=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,dvd.getName());
            stmt.setInt(2,dvd.getStatus());
            stmt.setInt(3,dvd.getLenCount());
            stmt.setDouble(4,dvd.getPrice());
            stmt.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement(querySql);
            stmt2.setString(1,dvd.getName());
            ResultSet rs = stmt2.executeQuery();
            if (rs.next()){
                dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setLenCount(rs.getInt("len_count"));
                dvd.setPrice(rs.getDouble("price"));
                dvd.setStatus(rs.getInt("status"));
                dvd.setName(rs.getString("dvdname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dvd;
    }

    @Override
    public void delById(int id, Connection conn) {
        String sql = "update dvd_dvd set status=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, State.ISDEL.getValue());
            stmt.setInt(2,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DVD dvd, Connection conn) {
        String sql = "update dvd_dvd set dvdname=?,status=?,len_count=?,price=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,dvd.getName());
            stmt.setInt(2,dvd.getStatus());
            stmt.setInt(3,dvd.getLenCount());
            stmt.setDouble(4,dvd.getPrice());
            stmt.setInt(5,dvd.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DVD getDVDById(int id) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_dvd where id=?";
        DVD dvd = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setLenCount(rs.getInt("len_count"));
                dvd.setPrice(rs.getDouble("price"));
                dvd.setStatus(rs.getInt("status"));
                dvd.setName(rs.getString("dvdname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return dvd;
    }

    @Override
    public List<DVD> listAll() {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_dvd";
        List<DVD> list = new ArrayList();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                DVD dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setLenCount(rs.getInt("len_count"));
                dvd.setPrice(rs.getDouble("price"));
                dvd.setStatus(rs.getInt("status"));
                dvd.setName(rs.getString("dvdname"));
                list.add(dvd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return list;
    }

    @Override
    public DVD getByName(String name) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_dvd where dvdname=?";
        DVD dvd = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setLenCount(rs.getInt("len_count"));
                dvd.setPrice(rs.getDouble("price"));
                dvd.setStatus(rs.getInt("status"));
                dvd.setName(rs.getString("dvdname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return dvd;
    }

    @Override
    public List<DVD> listByNameLike(String name) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_dvd where dvdname like ?";
        List<DVD> list = new ArrayList();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,"%"+name+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                DVD dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setLenCount(rs.getInt("len_count"));
                dvd.setPrice(rs.getDouble("price"));
                dvd.setStatus(rs.getInt("status"));
                dvd.setName(rs.getString("dvdname"));
                list.add(dvd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<DVD> listByStatus(State state) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_dvd where status=?";
        List<DVD> list = new ArrayList();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,state.getValue());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                DVD dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setLenCount(rs.getInt("len_count"));
                dvd.setPrice(rs.getDouble("price"));
                dvd.setStatus(rs.getInt("status"));
                dvd.setName(rs.getString("dvdname"));
                list.add(dvd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return list;
    }


}
