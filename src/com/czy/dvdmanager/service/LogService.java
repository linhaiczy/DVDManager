package com.czy.dvdmanager.service;

import com.czy.dvdmanager.entity.Lend;
import com.czy.dvdmanager.entity.Log;

import java.sql.Connection;
import java.util.List;

public interface LogService {
    void add(Log log, Connection conn);

    //添加借阅日志，同时添加借阅记录
    void addLendLog(int dvdId, Connection conn);

    //添加归还日志，同时添加归还记录
    void addBackLog(int dvdId, Connection conn);

    void delById(int id,Connection conn);

    void update(Log log,Connection conn);

    Log getById(int id);

    List<Log> listAllSortByTime();

    List<Log> listByDvdId(int dvdId);

    List<Log> ListInOneDay(long time);

    List<Log> listByUserId(int userId);

    List<Log> listByInfoLike(String info);

    List<Log> listUserLendLog(int userId);

}
