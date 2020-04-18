package com.service;

import com.dao.*;
import com.forms.UserAnswersForm;
import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import com.entities.UserAnswer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireService {
    private  QuestionnaireDao questionnaireDao;
    private  QuestionDao questionDao;
    private  AnswerDao answerDao;
    private  UserAnswerDao userAnswerDao;
    private  UserDao userDao;

    public QuestionnaireService(QuestionnaireDao questionnaireDao, QuestionDao questionDao, AnswerDao answerDao, UserAnswerDao userAnswerDao, UserDao userDao) {
        this.questionnaireDao = questionnaireDao;
        this.questionDao = questionDao;
        this.answerDao = answerDao;
        this.userAnswerDao = userAnswerDao;
        this.userDao = userDao;
    }

    @Transactional
    public List allQuestionnaires() {
        return questionnaireDao.getAll();
    }


    @Transactional
    public void add(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        questionnaireDao.add(questionnaire, map);
    }


    @Transactional
    public void delete(int questionnaireId) {
        questionnaireDao.delete(questionnaireDao.get(questionnaireId));

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
        questionnaireDao.delete(questionnaireId);*/
    }

    @Transactional
    public void update(Questionnaire questionnaire, Map<Question,List<Answer>> map) {
        questionnaireDao.update(questionnaire);
    }

    @Transactional
    public Questionnaire get(int id) {
        return questionnaireDao.get(id);
    }


    @Transactional
    public Map<Question, List<Answer>> getMap(int questionnaireId) {
        Map<Question, List<Answer>> result = new HashMap<>();

        List<Question> questions = getQuestions(questionnaireId);

        for (Question question : questions) {
            List<Answer> answers = questionDao.getAnswers(question);
            result.put(question, answers);
        }

        return result;
    }


    @Transactional
    public List<UserAnswersForm> getUserAnswers(int questionnaireId) {
        List<UserAnswer> userAnswers = userAnswerDao.getUserAnswers(questionnaireId);
        List<UserAnswersForm> userAnswersFormList = new ArrayList<>();
        if (!userAnswers.isEmpty()) {
            for (UserAnswer userAnswer : userAnswers) {
                UserAnswersForm answer = new UserAnswersForm();
                answer.setQuestion(questionDao.get(userAnswer.getQuestion_id()).getName());
                answer.setQuestionnaireTitle(questionnaireDao.get(questionnaireId).getTitle());
                answer.setValue(userAnswer.getValue());
                userAnswersFormList.add(answer);
            }
        }
        return userAnswersFormList;
    }

    @Transactional
    public List<Question> getQuestions(int questionnaireId) {
        List<Question> result = new ArrayList<>();
        List questions = questionnaireDao.getQuestions(questionnaireId);

        for (Object object : questions) {
            Question question = (Question)object;
            result.add(question);
        }
        return result;
    }

    @Transactional
    public int[] numberOfAnswers(int userId) {
        List<Questionnaire> questionnaires = userDao.getQuestionnaireList(userId);

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

