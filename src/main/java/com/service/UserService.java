package com.service;

import com.model.User;

import java.util.List;


public interface UserService {
    List allUsers();
    int add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
    int findUser(User user);
}