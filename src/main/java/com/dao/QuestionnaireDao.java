package com.dao;

import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
        session.beginTransaction();
        return (int) session.save(questionnaire);
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
        session.beginTransaction();
        session.update(questionnaire);

    }

    @Override
    public void persist(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();

        session.save(questionnaire);

    }

}
