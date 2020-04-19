package com.dao;

import com.entities.Question;
import com.entities.Questionnaire;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionnaireDao implements Dao<Questionnaire> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Questionnaire> getAll() {
        return (List<Questionnaire>) sessionFactory.getCurrentSession().createQuery("From Questionnaire").list();

    }


    @Override
    public void delete(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(questionnaire);
    }

    public int save(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(questionnaire);
    }

    public void merge(Questionnaire questionnaire){
        Session session = sessionFactory.getCurrentSession();
        session.merge(questionnaire);
    }
    public Questionnaire get(int id) {
        return sessionFactory.getCurrentSession().get(Questionnaire.class, id);
    }


    public List<Question> getQuestions(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.QUESTION where questionnaire_id = " + id).addEntity(Question.class);
        return (List<Question>) query.list();
    }

    public void update(Questionnaire questionnaire) {
        Session session =sessionFactory.getCurrentSession();
        session.update(questionnaire);

    }

    @Override
    public void persist(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(questionnaire);

    }

    public void replicate(Questionnaire questionnaire, ReplicationMode replicationMode){
        Session session = sessionFactory.getCurrentSession();
        session.replicate(questionnaire,replicationMode);
    }

}
