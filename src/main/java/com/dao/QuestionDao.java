package com.dao;

import com.entities.Answer;
import com.entities.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class QuestionDao implements Dao<Question> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Question get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Question.class, id );

    }

    @Override
    public List<Question> getAll() {
      return (List<Question>) sessionFactory.openSession().createQuery("From Question").getResultList();

    }

    @Override
    public void persist(Question question) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(question);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Question question) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(question);
        tx1.commit();
        session.close();
    }


    @Override
    public void delete(Question question) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(question);
        tx1.commit();
        session.close();
    }

    public List<Answer> getAnswers(Question question) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.ANSWER where question_id = " + question.getId()).addEntity(Answer.class);
        return (List<Answer>) query.list();
    }
}
