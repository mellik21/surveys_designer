package com.service;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List allUsers() {
        return userDao.allUsers();
    }

    @Override
    @Transactional
    public int add(User user) {
       return userDao.add(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    @Transactional
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public int findUser(User user) {
       return userDao.findUser(user);
    }

}
