package com.dao;

import com.entities.Answer;
import com.entities.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
      return (List<Question>)sessionFactory.getCurrentSession().createQuery("From Question").getResultList();

    }

    @Override
    public void persist(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(question);

    }

    public int save(Question question){
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(question);
    }

    @Override
    public void update(Question question) {
        Session session = sessionFactory.getCurrentSession();

        session.update(question);

    }


    @Override
    public void delete(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(question);

    }

    public List<Answer> getAnswers(Question question) {
        Session session =sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Answer where questionId = : questionId");
                query.setParameter("questionId",question.getId());
        return (List<Answer>) query.list();
    }

    public void merge(Question question){
        Session session =sessionFactory.getCurrentSession();
        session.merge(question);
    }

}
