package com.czy.dvdmanager.dao;

import com.czy.dvdmanager.entity.DVD;
import com.czy.dvdmanager.entity.Lend;
import com.czy.dvdmanager.entity.Log;
import com.czy.dvdmanager.enums.State;

import java.sql.Connection;
import java.util.List;

public interface LendDao {
    Lend findById(int id);

    List<Lend> findAll();

    List<Lend> findByDVDId(int id);

    List<Lend> findByStatus(State state);


    void save(Lend lend, Connection conn);

    void deleteById(int id, Connection conn);

    void update(Lend lend, Connection conn);
}
