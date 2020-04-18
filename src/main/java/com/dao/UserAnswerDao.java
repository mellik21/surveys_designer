package com.dao;

import com.entities.UserAnswer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        return sessionFactory.openSession().get(UserAnswer.class, id);
    }

    @Override
    public List<UserAnswer> getAll() {
        return (List<UserAnswer>) sessionFactory.openSession().createQuery("FROM UserAnswer").getResultList();
    }

    @Override
    public void persist(UserAnswer userAnswer) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(userAnswer);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(UserAnswer userAnswer) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.update(userAnswer);
        t.commit();
        session.close();
    }

    @Override
    public void delete(UserAnswer userAnswer) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(userAnswer);
        tx1.commit();
        session.close();
    }

    public List<UserAnswer> getUserAnswers(int questionnaireId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.USERANSWER where questionnaire_id = " + questionnaireId).addEntity(UserAnswer.class);
        return (List<UserAnswer>)query.getResultList();
    }
}
