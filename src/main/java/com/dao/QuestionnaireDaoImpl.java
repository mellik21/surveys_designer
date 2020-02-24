package com.dao;

import com.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionnaireDaoImpl implements QuestionnaireDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Questionnaire> allQuestionnaires() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Questionnaire ").list();
    }

    @Override
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
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getById(id)); //questionnaire deleting
        // List<Question> questions = getQuestions(id);

/*
        Query newQuery = session.createQuery("from Answer a, Question q where question_id =:id");
        newQuery.setParameter("id",id);

        Query query = session.createQuery("delete from Question where questionnare_id =:id");
        query.setParameter("id",id);

*/
    }

    @Override
    public void edit(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.update(questionnaire);
    }

    @Override
    public Questionnaire getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Questionnaire.class, id);
    }

    @Override
    public List getUserQuestionnaires(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Questionnaire q where q.user_id= :user_id");
        return (query
                .setParameter("user_id", id).getResultList());
    }

    @Override
    public List<Question> getQuestions(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.QUESTION where questionnaire_id = " + id).addEntity(Question.class);
        return (List<Question>) query.list();
    }

    @Override
    public List<Answer> getAnswers(int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.ANSWER where question_id = " + questionId).addEntity(Answer.class);
        return (List<Answer>) query.list();
    }

    @Override
    public Map<Question, List<Answer>> getQuestionnaireInformation(int questionnaireId) {
        Session session = sessionFactory.getCurrentSession();
        Map<Question, List<Answer>> result = new HashMap<>();

        List<Question> questions = getQuestions(questionnaireId);
        for (Question q : questions) {
            List<Answer> answers = getAnswers(q.getId());
            result.put(q, answers);
        }
        System.out.println();
        return result;
    }

    @Override
    public List<UserAnswer> getUserAnswers(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM SURVEYS.USERANSWER where questionnaire_id = " + id).addEntity(UserAnswer.class);
        return (List<UserAnswer>) query.list();
    }

    @Override
    public int[] numberOfAnswers(int userId) {
        Session session = sessionFactory.getCurrentSession();
        List questionnaires = getUserQuestionnaires(userId);
        int[] numberOfAnswers = new int[questionnaires.size()];
        int i = 0;
        for (Object object : questionnaires) {
            Questionnaire questionnaire = (Questionnaire) object;
            Query query = session.createSQLQuery("SELECT * FROM SURVEYS.USERANSWER where questionnaire_id = " + questionnaire.getId()).addEntity(UserAnswer.class);

            numberOfAnswers[i] = (int) query.list().size();
            i++;
        }
        return numberOfAnswers;
    }

    @Override
    public Question getQuestionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Question.class, id);
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public Answer getOption(int optionId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Answer.class, optionId);
    }
}
