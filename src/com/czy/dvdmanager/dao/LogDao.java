package com.czy.dvdmanager.dao;

import com.czy.dvdmanager.entity.Log;

import java.sql.Connection;
import java.util.List;

public interface LogDao {

    void add(Log log, Connection conn);

    void delById(int id, Connection conn);

    void update(Log log, Connection conn);

    Log getById(int id);

    List<Log> listAll();

    List<Log> listByUserId(int userId);

    List<Log> listByDvdId(int dvdId);

    List<Log> listByOperateInfo(String info);

    List<Log> listUserLendLog(int userId);

}
