package com.forms;

import com.model.Answer;
import com.model.Question;

import java.lang.reflect.AnnotatedArrayType;
import java.util.*;

public class QuestionnaireForm {
    private String title;
    private String description;
    private Map<Question,List<Answer>> map = new HashMap<>();
    public QuestionnaireForm(){
    }

    public QuestionnaireForm(String[]questions, int userId) {
        Map<Question,List<Answer>> newMap = new HashMap<>();
        for(String question : questions){
           List<Answer> answerList = new ArrayList<>();
           Question q = new Question();
           String[]finalArray = question.split("/");
           q.setType(finalArray[0]);
           q.setName(finalArray[1]);
           for(int i=2;i<finalArray.length;i++)
           {
               Answer answer = new Answer();
               answer.setName(finalArray[i]);
               answerList.add(answer);
           }
           q.setSize(finalArray.length-2);
          newMap.put(q,answerList);
       }
        map=newMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
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
}
