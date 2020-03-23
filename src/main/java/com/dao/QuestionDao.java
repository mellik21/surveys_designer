package com.dao;

import com.entities.Answer;
import com.entities.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDao implements Dao<Question>{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Question> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Question> getAll() {
        return null;
    }

    @Override
    public void save(Question question) {

    }

    @Override
    public void update(Question question, String[] params) {

    }

    @Override
    public void delete(Question question) {

    }

    public Question get(int id) {
        return null;
    }

    public List<Answer> getAnswers(Question question) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.ANSWER where question_id = " + question.getId()).addEntity(Answer.class);
        return (List<Answer>) query.list();
    }
}
