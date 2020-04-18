package com.dao;

import com.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuestionnaireDao implements Dao<Questionnaire>{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Questionnaire> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Questionnaire ").list();
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
        Session session = sessionFactory.getCurrentSession();
        return session.get(Questionnaire.class, id);
    }


    public List<Question> getQuestions(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.QUESTION where questionnaire_id = " + id).addEntity(Question.class);
        return (List<Question>) query.list();
    }

/*
    public void edit(Questionnaire questionnaire, Map<Question,List<Answer>> map ) {
        Session session = sessionFactory.getCurrentSession();
        int id = questionnaire.getId();


        for (Map.Entry<Question, List<Answer>> entry : map.entrySet()) {
            Question question = entry.getKey();
            System.out.println("Q:"+question.toString());
            List<Answer> answers = entry.getValue();
            question.setQuestionnaire_id(questionnaire.getId());
            int questionId = (int) session.save(question); //question saving
            session.saveOrUpdate(question);
            for (Answer answer : answers) {
                System.out.println("A:"+answer.toString());
                answer.setQuestion_id(questionId);
                session.saveOrUpdate(answer);
            }
        }
    }*/

    public void update(Questionnaire questionnaire){
        Session session = sessionFactory.getCurrentSession();
        Questionnaire existing = session.find(Questionnaire.class,questionnaire.getId());
        if(existing!=null){
            existing.merge(questionnaire);
        }
    }

    @Override
    public void save(Questionnaire questionnaire) {

    }

}
