package com.czy.dvdmanager.service;

import com.czy.dvdmanager.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {

    /**
     * 登录
     * @param username 姓名
     * @param password 密码
     * @return 用户实体类信息
     */
    User login(String username,String password, Connection conn) throws Exception;

    void register(User user, Connection conn) throws Exception;

    List<User> listDelUser();

    List<User> listNotDelUser();

    void delUserById(int userId, Connection conn);

    void updateById(User user, Connection conn);

    User getById(int id);

}
