package com.dao;

import com.entities.Answer;
import com.entities.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(answer);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Answer answer) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        System.out.println("ID ID ID ID ID:"+answer.getId());
        session.update(answer);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Answer answer) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(answer);
        tx1.commit();
        session.close();
    }



}
