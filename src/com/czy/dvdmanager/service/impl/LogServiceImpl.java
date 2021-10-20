package com.czy.dvdmanager.service.impl;

import com.czy.dvdmanager.dao.LogDao;
import com.czy.dvdmanager.entity.Lend;
import com.czy.dvdmanager.entity.Log;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.service.LendService;
import com.czy.dvdmanager.service.LogService;
import com.czy.dvdmanager.util.MyUtil;
import com.czy.dvdmanager.view.DVDMgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogServiceImpl implements LogService {
    private static LogService logService;

    private LogDao logDao = (LogDao) MyUtil.getInstance("LOG_DAO");
    private LendService lendService = (LendService) MyUtil.getInstance("LEND_SERVICE");

    public static LogService getInstance(){
        if (logService == null){
            logService = new LogServiceImpl();
        }
        return logService;
    }

    private LogServiceImpl() {
    }

    @Override
    public void add(Log log, Connection conn) {
        logDao.add(log,conn);
    }

    @Override
    public void addLendLog(int dvdId, Connection conn) {
        try {
            conn.setAutoCommit(false);

            lendService.lendRecord(dvdId,conn);

            Log log = new Log();
            log.setUserId(DVDMgr.userId);
            log.setOperateTime(System.currentTimeMillis());
            log.setDvdId(dvdId);
            log.setOperateInfo("借阅");
            logDao.add(log,conn);


            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void addBackLog(int dvdId, Connection conn) {
        try {
            conn.setAutoCommit(false);

            lendService.backRecord(dvdId,conn);

            Log log = new Log();
            log.setUserId(DVDMgr.userId);
            log.setOperateTime(System.currentTimeMillis());
            log.setDvdId(dvdId);
            log.setOperateInfo("归还");
            logDao.add(log,conn);


            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void delById(int id, Connection conn) {
        logDao.delById(id,conn);
    }

    @Override
    public void update(Log log, Connection conn) {
        logDao.update(log,conn);
    }

    @Override
    public Log getById(int id) {
        return logDao.getById(id);
    }

    @Override
    public List<Log> listAllSortByTime() {
        return logDao.listAll().stream()
                .sorted(Comparator.comparing(Log::getOperateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> listByDvdId(int dvdId) {
        return logDao.listByDvdId(dvdId);
    }

    @Override
    public List<Log> ListInOneDay(long time) {
        return logDao.listAll().stream()
                .filter(log -> log.getOperateTime()>time && log.getOperateTime()<MyUtil.getOneDayAfter(time))
                .sorted(Comparator.comparing(Log::getOperateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> listByUserId(int userId) {
        return logDao.listByUserId(userId);
    }

    @Override
    public List<Log> listByInfoLike(String info) {
        return logDao.listByOperateInfo(info).stream()
                .sorted(Comparator.comparing(Log::getOperateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> listUserLendLog(int userId) {
        return logDao.listUserLendLog(userId);
    }

}
