package com.czy.dvdmanager.service.impl;

import com.czy.dvdmanager.dao.DVDDao;
import com.czy.dvdmanager.dao.LogDao;
import com.czy.dvdmanager.entity.DVD;
import com.czy.dvdmanager.entity.Log;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.service.DVDService;
import com.czy.dvdmanager.service.LogService;
import com.czy.dvdmanager.util.DBHelper;
import com.czy.dvdmanager.util.MyUtil;
import com.czy.dvdmanager.view.DVDMgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DVDServiceImpl implements DVDService {

    private static DVDService dvdService;

    private DVDServiceImpl(){}

    public static DVDService getInstance(){
        if (dvdService == null){
            dvdService = new DVDServiceImpl();
        }
        return dvdService;
    }

    private DVDDao dvdDao = (DVDDao) MyUtil.getInstance("DVD_DAO");
    private LogDao logDao = (LogDao) MyUtil.getInstance("LOG_DAO");
    private LogService logService = (LogService) MyUtil.getInstance("LOG_SERVICE");

    private void addLog(String info,int dvdId,Connection conn){
        Log log = new Log();
        log.setOperateInfo(info);
        log.setDvdId(dvdId);
        log.setOperateTime(System.currentTimeMillis());
        log.setUserId(DVDMgr.userId);

        logDao.add(log,conn);
    }

    @Override
    public void add(DVD dvd, Connection conn) throws Exception {

        if (dvdDao.getByName(dvd.getName()) != null) {
            throw new Exception("该DVD已存在");
        }

        try {
            conn.setAutoCommit(false);

            //设置dvd的默认变量
            dvd.setLenCount(0);
            dvd.setStatus(State.NOTLEND.getValue());
            dvd = dvdDao.add(dvd,conn);//添加

//            dvd = dvdDao.getByName(dvd.getName());

            addLog("添加",dvd.getId(),conn);

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void delById(int id, Connection conn) throws Exception {
        DVD dvd = dvdDao.getDVDById(id);
        if (dvd == null || dvd.getStatus() == State.ISLEND.getValue()){
            throw new Exception("DVD状态错误");
        }
        try {
            conn.setAutoCommit(false);

            dvdDao.delById(id,conn);

            addLog("删除",id,conn);

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(DVD dvd, Connection conn) {
        try {
            conn.setAutoCommit(false);

            dvdDao.update(dvd,conn);

            addLog("修改",dvd.getId(),conn);

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public DVD getById(int id) {

        return dvdDao.getDVDById(id);
    }

    @Override
    public List<DVD> listLend() {
        Connection conn = DBHelper.getConnection();

        List<DVD> dvdList = dvdDao.listByStatus(State.ISLEND);
        addLog("查询已借出",0,conn);
        return dvdList;
    }

    @Override
    public List<DVD> listNotLend() {
        Connection conn = DBHelper.getConnection();
        List<DVD> dvdList = dvdDao.listByStatus(State.NOTLEND);
        addLog("查询未借出",0,conn);

        return dvdList;
    }

    @Override
    public List<DVD> listAll() {
        Connection conn = DBHelper.getConnection();
        List<DVD> dvdList = dvdDao.listAll();
        addLog("查询所有",0,conn);

        return dvdList;
    }

    @Override
    public List<DVD> listHasDel() {
        Connection conn = DBHelper.getConnection();
        List<DVD> dvdList = dvdDao.listAll().stream()
                .filter(dvd -> dvd.getStatus() == State.ISDEL.getValue())
                .sorted(Comparator.comparing(DVD::getLenCount))
                .collect(Collectors.toList());
        addLog("查询已删除",0,conn);
        DBHelper.closeConnection(conn);
        return dvdList;
    }

    @Override
    public List<DVD> listNotDel() {
        Connection conn = DBHelper.getConnection();
        List<DVD> dvdList = dvdDao.listAll().stream()
                .filter(dvd -> dvd.getStatus() != State.ISDEL.getValue())
                .sorted(Comparator.comparing(DVD::getLenCount))
                .collect(Collectors.toList());
        addLog("查询未删除",0,conn);
        DBHelper.closeConnection(conn);
        return dvdList;
    }

    @Override
    public List<DVD> listByNameLike(String name) {
        Connection conn = DBHelper.getConnection();
        List<DVD> dvdList = dvdDao.listByNameLike(name).stream()
                .sorted(Comparator.comparing(DVD::getLenCount))
                .collect(Collectors.toList());
        addLog("按名查询",0,conn);

        return dvdList;
    }

    @Override
    public List<DVD> listUserLendLog(int userId) {
        List<DVD> dvdList = new ArrayList<>();
        for (Log log : logService.listUserLendLog(userId)) {
            dvdList.add(dvdService.getById(log.getDvdId()));
        }
        return dvdList;
    }

    @Override
    public List<DVD> listSortByLendCount() {
        Connection conn = DBHelper.getConnection();
        List<DVD> dvdList = dvdDao.listAll().stream()
                .filter(dvd -> dvd.getStatus() != State.ISDEL.getValue())//没有删除的dvd
                .sorted(Comparator.comparing(DVD::getLenCount).reversed())//数量逆向排序
                .collect(Collectors.toList());
        addLog("按借出数查询",0,conn);
        DBHelper.closeConnection(conn);
        return dvdList;
    }

    @Override
    public void lendDVD(int DVDid) throws Exception {
        /*
        借书逻辑：
            首先判断该id对应的dvd是否存在，如果不存在则返回异常
            存在，判断当前dvd状态，是否可借出
            可以借出则，开启事物
                修改dvd状态并保存
                添加日志
                添加借阅日志
                提交
         */
        DVD dvd = dvdDao.getDVDById(DVDid);

        if (dvd == null || dvd.getStatus() == State.ISLEND.getValue() || State.ISDEL.getValue() == dvd.getStatus()){
            throw new Exception("DVD状态异常");
        }

        Connection conn = DBHelper.getConnection();
        try {
            conn.setAutoCommit(false);

            dvd.setStatus(State.ISLEND.getValue());//修改状态为已借出
            dvd.setLenCount(dvd.getLenCount()+1);//借阅数+1
            dvdDao.update(dvd,conn);

            logService.addLendLog(DVDid,conn);//添加借阅日志和借阅记录

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            DBHelper.closeConnection(conn);
        }

    }

    @Override
    public void returnDVD(int dvdId) throws Exception {
        /*
        归还逻辑：
            通过id查询dvd，如果为空报异常
            如果不为空，判断dvd状态，如果已借出则可以正常归还
            开启事物，修改dvd状态
            添加日志
            提交
         */
        DVD dvd = dvdDao.getDVDById(dvdId);
        if (dvd == null || dvd.getStatus() != State.ISLEND.getValue()){
            throw new Exception("DVD状态错误");
        }

        Connection conn =DBHelper.getConnection();
        try {
            conn.setAutoCommit(false);
            //修改dvd状态并保存
            dvd.setStatus(State.NOTLEND.getValue());
            dvdDao.update(dvd,conn);

            //修改日志状态为已归还
            //查询个人的‘借阅’状态日志，然后修改为‘借阅(已归还)’
            //该片段的作用是防止出现两个人借阅同一个DVD时出现重复借阅
            List<Log> logList = logService.listUserLendLog(DVDMgr.userId);
            for (Log log : logList) {
                if (dvdId == log.getDvdId()){
                    log.setOperateInfo("借阅(已归还)");
                    logService.update(log,conn);
                    break;
                }
            }

            logService.addBackLog(dvdId,conn);

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBHelper.closeConnection(conn);
        }

    }
}
