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
    private QuestionnaireDao questionnaireDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private UserAnswerDao userAnswerDao;
    private UserDao userDao;

    public QuestionnaireService(QuestionnaireDao questionnaireDao, QuestionDao questionDao, AnswerDao answerDao, UserAnswerDao userAnswerDao, UserDao userDao) {
        this.questionnaireDao = questionnaireDao;
        this.questionDao = questionDao;
        this.answerDao = answerDao;
        this.userAnswerDao = userAnswerDao;
        this.userDao = userDao;
    }

    @Transactional
    public List<Questionnaire> getAll() {
        return questionnaireDao.getAll();
    }


    @Transactional
    public void add(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        questionnaireDao.add(questionnaire, map);
    }


    @Transactional
    public void delete(int id) {
        Questionnaire questionnaire = questionnaireDao.get(id);
        questionnaireDao.delete(questionnaire);
    }

    @Transactional
    public void update(Questionnaire questionnaire, Map<Question,List<Answer>> map) {
        System.out.println("UPDATE");
        questionnaireDao.update(questionnaire);
        for(Map.Entry<Question,List<Answer>> entry : map.entrySet()){
            Question question = entry.getKey();
            if(question.getId()!=-1) {
                questionDao.update(question);
            }else {
                questionDao.persist(question);
            }
            for(Answer answer : entry.getValue()){
                answerDao.update(answer);
            }
        }
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
        for (Questionnaire questionnaire : questionnaires) {
            List<UserAnswer> answers = userAnswerDao.getUserAnswers(questionnaire.getId());
            numberOfAnswers[i] = answers.size();
            System.out.println(answers.size());
            i++;
        }
        return numberOfAnswers;
    }



}

