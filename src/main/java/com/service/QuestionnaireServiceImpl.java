package com.service;

import com.dao.QuestionnaireDao;
import com.dao.QuestionnaireDaoImpl;
import com.forms.UserAnswersForm;
import com.model.Answer;
import com.model.Question;
import com.model.Questionnaire;
import com.model.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private QuestionnaireDao questionnaireDao;

    @Autowired
    public void setUserDao(QuestionnaireDaoImpl questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    @Override
    @Transactional
    public List allQuestionnaires() {
        return questionnaireDao.allQuestionnaires();
    }

    @Override
    @Transactional
    public void add(Questionnaire questionnaire, Map<Question,List<Answer>> map ) {
        questionnaireDao.add(questionnaire, map);
    }

    @Override
    @Transactional
    public void edit(Questionnaire questionnaire, Map<Question,List<Answer>> map ) {
        questionnaireDao.edit(questionnaire, map);
    }



    @Override
    @Transactional
    public void delete(int id) {
        questionnaireDao.delete(id);
    }

    @Override
    @Transactional
    public void edit(Questionnaire questionnaire) {
        questionnaireDao.edit(questionnaire);
    }

    @Override
    @Transactional
    public Questionnaire getById(int id) {
        return questionnaireDao.getById(id);
    }

    @Override
    @Transactional
    public List<Questionnaire> getUserQuestionnaires(int id) {
        return questionnaireDao.getUserQuestionnaires(id);
    }

    @Override
    @Transactional
    public Map<Question, List<Answer>> getQuestionnaireInfo(int questionnaireId) {
        return questionnaireDao.getQuestionnaireInformation(questionnaireId);
    }

    @Override
    @Transactional
    public List<UserAnswersForm> getUserAnswers(int questionnaireId) {
        List<UserAnswer> userAnswers = questionnaireDao.getUserAnswers(questionnaireId);
        List<UserAnswersForm> userAnswersFormList = new ArrayList<>();
        if(!userAnswers.isEmpty()){
            for (UserAnswer userAnswer : userAnswers) {
                UserAnswersForm answer = new UserAnswersForm();
                answer.setQuestion(questionnaireDao.getQuestionById(userAnswer.getQuestion_id()).getName());
                answer.setQuestionnaireTitle(questionnaireDao.getById(questionnaireId).getTitle());
                answer.setUsername(questionnaireDao.getUserById(userAnswer.getUser_id()).getLogin());
                answer.setValue(userAnswer.getValue());
                userAnswersFormList.add(answer);
            }
        }
        return userAnswersFormList;
    }

    @Override
    @Transactional
    public List<Question> getQuestions(int questionnaireId) {
        return questionnaireDao.getQuestions(questionnaireId);
    }

    @Override
    @Transactional
    public Question getQuestion(int questionId) {
        return questionnaireDao.getQuestionById(questionId);
    }

    @Override
    @Transactional
    public List<Answer> getOptions(int questionId) {
        return questionnaireDao.getAnswers(questionId);
    }

    @Override
    @Transactional
    public int[]numberOfAnswers(int userId) {
        return questionnaireDao.numberOfAnswers(userId);
    }

    @Override
    @Transactional
    public Answer getOption(int optionId) {
        return questionnaireDao.getOption(optionId);
    }




}

