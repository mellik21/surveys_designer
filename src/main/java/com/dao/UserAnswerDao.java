package com.dao;

import com.entities.UserAnswer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserAnswerDao implements Dao<UserAnswer> {
    private SessionFactory sessionFactory;

    @Override
    public Optional<UserAnswer> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<UserAnswer> getAll() {
        return null;
    }

    @Override
    public void save(UserAnswer userAnswer) {

    }

    @Override
    public void update(UserAnswer userAnswer, String[] params) {

    }

    @Override
    public void delete(UserAnswer userAnswer) {

    }

    public List getUserAnswers(int questionnaireId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.USERANSWER where questionnaire_id = " + questionnaireId).addEntity(UserAnswer.class);
        return query.getResultList();
    }
}
