package com.dao;

import com.model.User;

import java.util.List;

public interface UserDao {
    List allUsers();
    int add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
    int findUser(User user);
}