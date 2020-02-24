package com.service;

import com.forms.UserAnswersForm;
import com.model.Answer;
import com.model.Question;
import com.model.Questionnaire;
import com.model.UserAnswer;

import java.util.List;
import java.util.Map;

public interface QuestionnaireService {
    List allQuestionnaires();
    void add(Questionnaire questionnaire, Map<Question,List<Answer>> map);
    void delete(int id);
    void edit(Questionnaire questionnaire);
    Questionnaire getById(int id);
    List<Questionnaire> getUserQuestionnaires(int id);
    Map<Question, List<Answer>> getQuestionnaireInfo(int questionnaireId);
    List<UserAnswersForm> getUserAnswers(int questionnaireId);
    List<Question> getQuestions(int questionnaireId);

    Question getQuestion(int questionId);

    List<Answer> getOptions(int questionId);

    Answer getOption(int optionId);
    int []numberOfAnswers(int userId);
}
