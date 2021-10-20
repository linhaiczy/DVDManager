package com.czy.dvdmanager.dao;

import com.czy.dvdmanager.entity.DVD;
import com.czy.dvdmanager.enums.State;

import java.sql.Connection;
import java.util.List;

public interface DVDDao {

    DVD add(DVD dvd, Connection conn);

    void delById(int id, Connection conn);

    void update(DVD dvd, Connection conn);

    DVD getDVDById(int id);

    List<DVD> listAll();

    DVD getByName(String name);

    List<DVD> listByNameLike(String name);

    List<DVD> listByStatus(State state);

}
