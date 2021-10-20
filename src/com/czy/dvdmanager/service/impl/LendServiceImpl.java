package com.czy.dvdmanager.service.impl;

import com.czy.dvdmanager.dao.LendDao;
import com.czy.dvdmanager.entity.Lend;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.service.LendService;
import com.czy.dvdmanager.util.MyUtil;

import java.sql.Connection;

public class LendServiceImpl implements LendService {
    private static LendService lendService;

    public static LendService getInstance(){
        if (lendService == null){
            lendService = new LendServiceImpl();
        }
        return lendService;
    }

    private LendServiceImpl(){}

    private LendDao lendDao = (LendDao) MyUtil.getInstance("LEND_DAO");

    @Override
    public void add(Lend lend, Connection conn) {
        lendDao.save(lend,conn);
    }

    @Override
    public void lendRecord(int dvdId, Connection conn) {
        Lend lend = new Lend();
        lend.setDvdId(dvdId);
        lend.setStatus(State.ISLEND.getValue());
        lend.setLendTime(System.currentTimeMillis());
        lendDao.save(lend,conn);
    }

    @Override
    public void backRecord(int dvdId, Connection conn) {
        Lend lend = new Lend();
        lend.setDvdId(dvdId);
        lend.setStatus(State.NOTLEND.getValue());
        lend.setLendTime(System.currentTimeMillis());
        lendDao.save(lend,conn);
    }
}
