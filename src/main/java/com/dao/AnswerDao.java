package com.dao;

import com.entities.Answer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerDao implements Dao<Answer> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Answer get(int id) {
        return sessionFactory.openSession().get(Answer.class, id) ;
    }

    @Override
    public List<Answer> getAll() {
        return (List<Answer>) sessionFactory.openSession().createQuery("From Answer").list();
    }

    @Override
    public void persist(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(answer);
    }

    @Override
    public void update(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(answer);
    }

    @Override
    public void delete(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(answer);
    }

    public void save(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(answer);
    }

    public void saveOrUpdate(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(answer);
    }
}
