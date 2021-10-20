package com.czy.dvdmanager.dao;

import com.czy.dvdmanager.entity.User;
import com.czy.dvdmanager.enums.State;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    User findById(int id);

    List<User> findAll();

    List<User> findByStatus(State state);

    void save(User user, Connection conn);

    void update(User user, Connection conn);

    void deleteById(int id, Connection conn);

    User getByName(String username);
}
