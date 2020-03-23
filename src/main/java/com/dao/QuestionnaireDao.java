package com.dao;

import com.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionnaireDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Questionnaire> allQuestionnaires() {
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

    //todo Добавить удаление вопросов и ответов при удалении опроса
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(get(id));
    }

    public void edit(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.update(questionnaire);
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

    public List<Answer> getAnswers(int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.ANSWER where question_id = " + questionId).addEntity(Answer.class);
        return (List<Answer>) query.list();
    }


    public List<UserAnswer> getUserAnswers(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.USERANSWER where questionnaire_id = " + id).addEntity(UserAnswer.class);
        return (List<UserAnswer>) query.list();
    }



    public Question getQuestionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Question.class, id);
    }

    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public Answer getOption(int optionId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Answer.class, optionId);
    }

    public void edit(Questionnaire questionnaire, Map<Question,List<Answer>> map ) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Q_ID = "+questionnaire.getId());
        //int questionnaireId = (int) session.save(questionnaire);
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
    }
}
