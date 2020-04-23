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
    public void delete(int id) {
        Questionnaire questionnaire = questionnaireDao.get(id);
        questionnaireDao.delete(questionnaire);
    }

    @Transactional
    public void update(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        /*
        Невозможно понять какие вопросы и ответы были удалены а какие нет
        На всякий случай удаляем все вопросы и ответы и добавляем заново
         */
        System.out.println("\n DELETE questionnaire \n");
        questionnaireDao.delete(questionnaire);

        add(questionnaire,map);
/*
        System.out.println("\n SAVE questionnaire \n");
        int id = questionnaireDao.save(questionnaire);


        for (Map.Entry<Question, List<Answer>> entry : map.entrySet()) {
            Question question = entry.getKey();
            question.setQuestionnaireId(id);
            System.out.println("\n SAVE Question"+question.getId()+" "+ question.getName()+"\n");
            int questionId;

            if(question.getId()!=-1){
                System.out.println("\n SAVE question \n");
                questionId = questionDao.save(question);

                System.out.println("\n UPDATE useranswer \n");
                List<UserAnswer>userAnswers = userAnswerDao.getByQuestion(questionId);
                for(UserAnswer userAnswer : userAnswers){
                    userAnswer.setQuestionId(questionId);
                    userAnswerDao.update(userAnswer);
                }
            }else{
                System.out.println("\n ADD question \n");
                questionId = questionDao.save(question);
            }

            for (Answer answer : entry.getValue()) {
                System.out.println(" \n ADD answer \n");
                answer.setQuestionId(questionId);
                answerDao.saveOrUpdate(answer);
            }
        }*/
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

        return  questionnaireDao.getQuestions(questionnaireId);
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
    public void add(Questionnaire questionnaire, Map<Question, List<Answer>> map) {
        int questionnaireId = questionnaireDao.save(questionnaire);//questionnaire saving

        for (Map.Entry<Question, List<Answer>> entry : map.entrySet()) {
            Question question = entry.getKey();
            List<Answer> answers = entry.getValue();
            question.setQuestionnaireId(questionnaireId);
            int questionId = questionDao.save(question); //question saving
            for (Answer answer : answers) {
                answer.setQuestionId(questionId);
                answerDao.persist(answer);//answer saving
            }
        }
    }

}

