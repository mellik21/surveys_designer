package com.forms;

import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;

import java.util.ArrayList;
import java.util.List;

public class CreatingForm{
  private  Questionnaire questionnaire = new Questionnaire();
    List<Question> questionList = new ArrayList<>();

    public CreatingForm(String[] questions, int user){
        String[]header = questions[0].split("/");
            questionnaire.setUserId(user);
            questionnaire.setTitle(header[0]);
            questionnaire.setDescription(header[1]);
            for(int i=1;i<questions.length;i++){
             Question question = new Question();
             String[]current = questions[i].split("/");
                question.setType(current[0]);
                question.setName(current[1]);

                List<Answer>answers = new ArrayList<>();
                for(int j=2;j<current.length;j++){
                    Answer answer = new Answer();
                    answer.setName(current[j]);
                    answers.add(answer);
                }
                question.setAnswers(answers);
                questionList.add(question);
            }
            questionnaire.setQuestions(questionList);
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}
