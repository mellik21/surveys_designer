package com.service;

import com.dao.UserDao;
import com.entities.Questionnaire;
import com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    public void add(User user) {
        userDao.persist(user);
    }

    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public User get(int id) {
        return userDao.get(id);
    }

    @Transactional
    public int find(User user) {
       return userDao.find(user);
    }

    @Transactional
    public List<Questionnaire> getQuestionnaireList(int id) {
        return userDao.getQuestionnaireList(id);
    }
}
