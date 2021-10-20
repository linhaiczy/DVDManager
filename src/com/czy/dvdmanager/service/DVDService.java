package com.czy.dvdmanager.service;

import com.czy.dvdmanager.entity.DVD;

import java.sql.Connection;
import java.util.List;

public interface DVDService {

    void add(DVD dvd, Connection conn) throws Exception;

    void delById(int id,Connection conn) throws Exception;

    void updateById(DVD dvd,Connection conn);

    DVD getById(int id);

    /**
     * 显示已借出
     * @return
     */
    List<DVD> listLend();

    /**
     * 显示未借出
     * @return
     */
    List<DVD> listNotLend();

    List<DVD> listAll();

    List<DVD> listHasDel();
    List<DVD> listNotDel();
    List<DVD> listByNameLike(String name);
    List<DVD> listUserLendLog(int userId);


    /**
     * 升序
     * @return
     */
    List<DVD> listSortByLendCount();

    /**
     * 借阅
     * @param DVDid
     */
    void lendDVD(int DVDid) throws Exception;

    /**
     * 归还
     * @param dvdId
     */
    void returnDVD(int dvdId) throws Exception;

}
