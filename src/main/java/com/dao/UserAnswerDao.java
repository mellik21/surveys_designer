package com.dao;

import com.entities.UserAnswer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAnswerDao implements Dao<UserAnswer> {

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserAnswer get(int id) {
        return sessionFactory.getCurrentSession().get(UserAnswer.class, id);
    }

    @Override
    public List<UserAnswer> getAll() {
        return (List<UserAnswer>) sessionFactory.getCurrentSession().createQuery("FROM UserAnswer").getResultList();
    }

    @Override
    public void persist(UserAnswer userAnswer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userAnswer);
    }

    @Override
    public void update(UserAnswer userAnswer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userAnswer);
    }

    @Override
    public void delete(UserAnswer userAnswer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(userAnswer);
    }

    public List<UserAnswer> getUserAnswers(int id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserAnswer where questionnaireId = : questionnaireId");
        query.setParameter("questionnaireId", id);
        return (List<UserAnswer>)query.getResultList();
    }
    public List<UserAnswer> getByQuestion(int id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserAnswer where questionId = : questionId");
        query.setParameter("questionId", id);
        return (List<UserAnswer>)query.getResultList();
    }
}
