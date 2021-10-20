package com.czy.dvdmanager.service.impl;

import com.czy.dvdmanager.dao.UserDao;
import com.czy.dvdmanager.entity.User;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.service.UserService;
import com.czy.dvdmanager.util.MyUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{
    private static UserService userService;

    private UserDao userDao = (UserDao) MyUtil.getInstance("USER_DAO");

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    private UserServiceImpl() {

    }

    @Override
    public User login(String username, String password, Connection conn) throws Exception {
        User user = userDao.getByName(username);

        if (user == null || !user.getPassword().equals(password)){
            throw new Exception("账号或密码错误");
        }

        return user;
    }

    @Override
    public void register(User user, Connection conn) throws Exception {
        User queryUser = userDao.getByName(user.getUserName());
        if (queryUser != null){
            throw new Exception("该用户已存在");
        }else {
            user.setStatus(State.NOTDEL.getValue());
            user.setCreatTime(System.currentTimeMillis());
            userDao.save(user,conn);
        }
    }

    @Override
    public List<User> listDelUser() {
        List<User> list = userDao.findByStatus(State.ISDEL);


        return null;
    }

    @Override
    public List<User> listNotDelUser() {
        List<User> list = userDao.findAll().stream()
                .filter(user -> user.getStatus() != State.ISLEND.getValue())
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public void delUserById(int userId, Connection conn) {
        userDao.deleteById(userId,conn);
    }

    @Override
    public void updateById(User user, Connection conn) {
        userDao.update(user,conn);
    }

    @Override
    public User getById(int id) {
        return userDao.findById(id);
    }
}
