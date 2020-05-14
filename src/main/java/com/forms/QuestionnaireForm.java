package com.forms;

import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;

import java.util.*;

public class QuestionnaireForm {
 //   private String title;
  //  private String description;
 //   private Map<Question, List<Answer>> map;
    private List<Question> questionList = new ArrayList<>();
    private Questionnaire questionnaire = new Questionnaire();

    public QuestionnaireForm(String[] questions, int user) {
        if(questions.length==1){
            String[]current = questions[0].split("/");
            questionnaire.setTitle(current[0]);
            questionnaire.setDescription(current[1]);
            questionnaire.setUserId(user);
            questionnaire.setSize(0);
        }else{
            convert(questions,user);
        }
    }

    public void convert(String[]questions, int user){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Integer> answerIds = new ArrayList<Integer>();

        int counter=0;
        int index=0;
        boolean header = false;

        for(int i=0;i<questions.length;i++){
            String[]current = questions[i].split("/");
            if(!header){
                questionnaire.setTitle(current[0]);
                questionnaire.setDescription(current[1]);
                header = true;
            }else{
                List<Answer> answerList = new ArrayList<>();
                Question q = new Question();
                int id;
                if(i<=counter*2) {
                    id = ids.get(i-counter-1);
                }else{
                    id=-1;
                }
                q.setId(id);
                q.setName(current[0]);
                q.setType(current[1]);

                for (int j = 2; j < current.length; j++) {
                    Answer answer = new Answer();
                    if(index<=answerIds.size() && answerIds.size()>0) {
                        answer.setId(answerIds.get(index));
                        index++;
                    }else if(answerIds.size()==0){
                        answer.setId(-1);
                    }
                    answer.setQuestionId(q.getId());
                    answer.setName(current[j]);
                    answerList.add(answer);
                }
                q.setSize(current.length - 2);
                q.setAnswers(answerList);
                questionList.add(q);
            }

        }
        questionnaire.setUserId(user);
        questionnaire.setSize(questionList.size());
        questionnaire.setQuestions(questionList);
    }
    public List<Question> getQuestionList() {
        return questionList;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }
}

    /*
    public QuestionnaireForm(String[] questions) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Integer> answerIds = new ArrayList<Integer>();

        int counter=0;
        int index=0;
        boolean header = false;

        Map<Question, List<Answer>> newMap = new HashMap<>();
        for(int i=0;i<questions.length;i++){
            String[]current = questions[i].split("/");

            if(current[0].equals("QID")){
                ids.add(Integer.parseInt(current[1]));
                counter++;
            }else if(current[0].equals("AID")){
                answerIds.add(Integer.parseInt(current[1]));
            }else if(!header){
                this.title = current[0];
                this.description = current[1];
                header = true;
            }else{
                List<Answer> answerList = new ArrayList<>();
                Question q = new Question();
                int id;
                if(i<=counter*2) {
                   id = ids.get(i-counter-1);
                }else{
                    id=-1;
                }
                q.setId(id);
                q.setName(current[0]);
                q.setType(current[1]);

                for (int j = 2; j < current.length; j++) {
                    Answer answer = new Answer();
                    if(index<=answerIds.size()) {
                        answer.setId(answerIds.get(index));
                        index++;
                    }
                    answer.setQuestionId(q.getId());
                    answer.setName(current[j]);
                    answerList.add(answer);
                }
                q.setSize(current.length - 2);
                newMap.put(q, answerList);
            }

        }
        map = newMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public Map<Question, List<Answer>> getMap() {
        return map;
    }

    public void setMap(Map<Question, List<Answer>> map) {
        this.map = map;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     */

