package com.service;

import com.dao.*;
import com.forms.UserAnswersForm;
import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import com.entities.UserAnswer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
        List<Questionnaire>questionnaires = questionnaireDao.getAll();
        for (Questionnaire questionnaire:questionnaires) {
            for(Question question : questionnaire.getQuestions()){
                question.getAnswers().iterator();
            }
        }
        return questionnaires;
    }

    @Transactional
    public void delete(int id) {
        Questionnaire questionnaire = questionnaireDao.get(id);
        questionnaireDao.delete(questionnaire);
    }

    @Transactional
    public Questionnaire get(int id) {
        Questionnaire questionnaire = questionnaireDao.get(id);
        for(Question question : questionnaire.getQuestions()){
            question.getAnswers().iterator();
        }
        return questionnaire;
    }

    @Transactional
    public List<UserAnswersForm> getUserAnswers(int questionnaireId) {
        List<UserAnswer> userAnswers = userAnswerDao.getUserAnswers(questionnaireId);
        List<UserAnswersForm> userAnswersFormList = new ArrayList<>();
        if (!userAnswers.isEmpty()) {
            for (UserAnswer userAnswer : userAnswers) {
                UserAnswersForm answer = new UserAnswersForm();
                answer.setQuestion(questionDao.get(userAnswer.getQuestionId()).getName());
                answer.setQuestionnaireTitle(questionnaireDao.get(questionnaireId).getTitle());
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


    @Transactional
    public void add(Questionnaire questionnaire) {
        int id = questionnaireDao.save(questionnaire);

        for (Question question : questionnaire.getQuestions()) {
            question.setQuestionnaireId(id);
            int questionId = questionDao.save(question);
            for (Answer answer : question.getAnswers()) {
                answer.setQuestionId(questionId);
                answerDao.save(answer);
            }
        }
    }

    @Transactional
    public void update(Questionnaire questionnaire) {
        questionnaireDao.update(questionnaire);

    }

    @Transactional
    public List<Questionnaire> search(String searchQuery){
       List<Questionnaire> result = questionnaireDao.search(searchQuery);
       for(Questionnaire questionnaire : result) {
           for(Question question : questionnaire.getQuestions()){
               question.getAnswers().iterator();
           }
       }
       return result;
    }



    public String getUniqueLinkName(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(40);
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
    }
}

