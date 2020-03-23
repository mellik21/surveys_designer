package com.service;

import com.dao.*;
import com.forms.UserAnswersForm;
import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import com.entities.UserAnswer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireService {
    private QuestionnaireDao questionnaireDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private UserAnswerDao userAnswerDao;
    private UserDao userDao;

    @Autowired
    public void setUserDao(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }


    @Transactional
    public List allQuestionnaires() {
        return questionnaireDao.allQuestionnaires();
    }


    @Transactional
    public void add(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        questionnaireDao.add(questionnaire, map);
    }

    @Transactional
    public void edit(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        questionnaireDao.edit(questionnaire, map);
    }

    @Transactional
    public void delete(int questionnaireId) {
        List<UserAnswer> userAnswers = questionnaireDao.getUserAnswers(questionnaireId);
        for (UserAnswer userAnswer : userAnswers) {
            userAnswerDao.delete(userAnswer);
        }
        Map<Question, List<Answer>> questionListMap = getMap(questionnaireId);
        for (Map.Entry<Question, List<Answer>> question : questionListMap.entrySet()) {
            List<Answer> answers = question.getValue();
            for (Answer answer : answers) {
                answerDao.delete(answer);
            }
            questionDao.delete(question.getKey());
        }
        questionnaireDao.delete(questionnaireId);
    }

    @Transactional
    public void edit(Questionnaire questionnaire) {
        questionnaireDao.edit(questionnaire);
    }

    @Transactional
    public Questionnaire getById(int id) {
        return questionnaireDao.get(id);
    }


    @Transactional
    public Map<Question, List<Answer>> getMap(int questionnaireId) {
        Map<Question, List<Answer>> result = new HashMap<>();

        List<Question> questions = getQuestions(questionnaireId);
        for (Question q : questions) {
            List<Answer> answers = questionnaireDao.getAnswers(q.getId());
            result.put(q, answers);
        }

        return result;
    }


    @Transactional
    public List<UserAnswersForm> getUserAnswers(int questionnaireId) {
        List<UserAnswer> userAnswers = questionnaireDao.getUserAnswers(questionnaireId);
        List<UserAnswersForm> userAnswersFormList = new ArrayList<>();
        if (!userAnswers.isEmpty()) {
            for (UserAnswer userAnswer : userAnswers) {
                UserAnswersForm answer = new UserAnswersForm();
                answer.setQuestion(questionnaireDao.getQuestionById(userAnswer.getQuestion_id()).getName());
                answer.setQuestionnaireTitle(questionnaireDao.get(questionnaireId).getTitle());
                answer.setUsername(questionnaireDao.getUserById(userAnswer.getUser_id()).getLogin());
                answer.setValue(userAnswer.getValue());
                userAnswersFormList.add(answer);
            }
        }
        return userAnswersFormList;
    }

    @Transactional
    public List<Question> getQuestions(int questionnaireId) {
        return questionnaireDao.getQuestions(questionnaireId);
    }


    @Transactional
    public Question getQuestion(int questionId) {
        return questionDao.get(questionId);
    }


    @Transactional
    public List<Answer> getOptions(int questionId) {
        return questionDao.getAnswers(questionId);
    }

    @Transactional
    public Answer getOption(int optionId) {
        return questionnaireDao.getOption(optionId);
    }


    @Transactional
    public int[] numberOfAnswers(int userId) {
        List questionnaires = userDao.getQuesionnaireList(userId);
        int[] numberOfAnswers = new int[questionnaires.size()];
        int i = 0;
        for (Object object : questionnaires) {
            Questionnaire questionnaire = (Questionnaire) object;
            List answers = userAnswerDao.getUserAnswers(questionnaire.getId());
            numberOfAnswers[i] = answers.size();
            i++;
        }
        return numberOfAnswers;
    }
}

