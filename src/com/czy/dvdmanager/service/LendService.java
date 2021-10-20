package com.czy.dvdmanager.service;

import com.czy.dvdmanager.entity.Lend;

import java.sql.Connection;

public interface LendService {

    void add(Lend lend, Connection conn);

    void lendRecord(int dvdId, Connection conn);

    void backRecord(int dvdId, Connection conn);
}
