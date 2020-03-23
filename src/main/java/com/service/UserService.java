package com.service;

import com.dao.UserDao;
import com.entities.Questionnaire;
import com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public List allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    public int add(User user) {
       return userDao.add(user);
    }

    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public int findUser(User user) {
       return userDao.findUser(user);
    }

    @Transactional
    public List<Questionnaire> getQuesionnaireList(int id) {
        List<Questionnaire> questionnaires = new ArrayList<>();
        List list = userDao.getQuestionnaireList(id);
        for(Object object:list){
            questionnaires.add((Questionnaire)object);
        }
        return questionnaires;
    }
}
