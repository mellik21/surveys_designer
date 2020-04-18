package com.dao;

import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QuestionnaireDao implements Dao<Questionnaire>{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Questionnaire> getAll() {
        return (List<Questionnaire>) sessionFactory.openSession().createQuery("From Questionnaire").list();

    }


    public void add(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        Session session = sessionFactory.getCurrentSession();
        int questionnaireId = (int) session.save(questionnaire);//questionnaire saving

        for (Map.Entry<Question, List<Answer>> entry : map.entrySet()) {
            Question question = entry.getKey();
            List<Answer> answers = entry.getValue();
            question.setQuestionnaire_id(questionnaireId);
            int questionId = (int) session.save(question); //question saving
            for (Answer answer : answers) {
                answer.setQuestion_id(questionId);
                session.persist(answer);//answer saving
            }
        }
    }


    @Override
    public void delete(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(questionnaire);
    }


    public Questionnaire get(int id) {
        return sessionFactory.openSession().get(Questionnaire.class, id);
    }


    public List<Question> getQuestions(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.QUESTION where questionnaire_id = " + id).addEntity(Question.class);
        return (List<Question>) query.list();
    }

    public void update(Questionnaire questionnaire){
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(questionnaire);
        tx1.commit();
        session.close();
    }

    @Override
    public void persist(Questionnaire questionnaire) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(questionnaire);
        tx1.commit();
        session.close();
    }

}
