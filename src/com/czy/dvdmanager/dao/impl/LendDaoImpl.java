package com.czy.dvdmanager.dao.impl;

import com.czy.dvdmanager.dao.LendDao;
import com.czy.dvdmanager.entity.DVD;
import com.czy.dvdmanager.entity.Lend;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LendDaoImpl implements LendDao {
    private static LendDaoImpl lendDao;

    private LendDaoImpl() {
    }

    public static LendDao getInstance(){
        if (lendDao == null){
            lendDao = new LendDaoImpl();
        }
        return lendDao;
    }

    @Override
    public Lend findById(int id) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_lend where id=?";
        Lend lend = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                lend = new Lend();
                lend.setId(rs.getInt("id"));
                lend.setStatus(rs.getInt("status"));
                lend.setLendTime(rs.getLong("lend_time"));
                lend.setDvdId(rs.getInt("dvd_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }
        return lend;
    }

    @Override
    public List<Lend> findAll() {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_lend";
        List<Lend> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lend lend = new Lend();
                lend.setId(rs.getInt("id"));
                lend.setStatus(rs.getInt("status"));
                lend.setLendTime(rs.getLong("lend_time"));
                lend.setDvdId(rs.getInt("dvd_id"));
                list.add(lend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }

        return list;
    }

    @Override
    public List<Lend> findByDVDId(int id) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_lend where dvd_id=?";
        List<Lend> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lend lend = new Lend();
                lend.setId(rs.getInt("id"));
                lend.setStatus(rs.getInt("status"));
                lend.setLendTime(rs.getLong("lend_time"));
                lend.setDvdId(rs.getInt("dvd_id"));
                list.add(lend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }

        return list;
    }

    @Override
    public List<Lend> findByStatus(State state) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from dvd_lend where status=?";
        List<Lend> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,state.getValue());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lend lend = new Lend();
                lend.setId(rs.getInt("id"));
                lend.setStatus(rs.getInt("status"));
                lend.setLendTime(rs.getLong("lend_time"));
                lend.setDvdId(rs.getInt("dvd_id"));
                list.add(lend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }

        return list;
    }

    @Override
    public void save(Lend lend, Connection conn) {
        String sql = "insert into dvd_lend (dvd_id,lend_time,status,a,b) values (?,?,?,default ,default )";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,lend.getDvdId());
            stmt.setLong(2,lend.getLendTime());
            stmt.setInt(3,lend.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id, Connection conn) {
        String sql = "update dvd_lend set status=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,State.ISDEL.getValue());
            stmt.setInt(2,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Lend lend, Connection conn) {
        String sql = "update dvd_lend set dvd_id=?,lend_time=?,status=? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,lend.getDvdId());
            stmt.setLong(2,lend.getLendTime());
            stmt.setInt(3,lend.getStatus());
            stmt.setInt(4,lend.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
