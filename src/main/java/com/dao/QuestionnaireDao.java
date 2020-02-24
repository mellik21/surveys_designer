package com.dao;

import com.model.*;

import java.util.List;
import java.util.Map;

public interface QuestionnaireDao {
    List<Questionnaire>allQuestionnaires();
    void add(Questionnaire questionnaire, Map<Question,List<Answer>> map);
    void delete(int id);
    void edit(Questionnaire questionnaire);
    Questionnaire getById(int id);
    List<Questionnaire> getUserQuestionnaires(int id);
    List<Question>getQuestions(int id);

    List<Answer> getAnswers(int id);
    Map<Question, List<Answer>> getQuestionnaireInformation(int questionnaireId);

    List<UserAnswer> getUserAnswers(int questionnaireId);

    Question getQuestionById(int id);

    User getUserById(int user_id);

    Answer getOption(int optionId);
    int[]numberOfAnswers(int userId);
}
