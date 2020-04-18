package com.forms;

import com.entities.Answer;
import com.entities.Question;

import java.util.*;

public class QuestionnaireForm {
    private String title;
    private String description;
    private Map<Question, List<Answer>> map = new HashMap<>();

    public QuestionnaireForm(String[] questions, int userId) {
        String[]header = questions[0].split("/");
        this.title = header[0];
        this.description = header[1];
        System.out.println("title = "+title+'\n' +"descr = "+description+'\n');
        Map<Question, List<Answer>> newMap = new HashMap<>();
        for (int i = 1; i < questions.length;i++) {

            List<Answer> answerList = new ArrayList<>();
            Question q = new Question();
            String[] finalArray = questions[i].split("/");
            q.setName(finalArray[0]);
            q.setType(finalArray[1]);

            for (int j = 2; j < finalArray.length; j++) {
                Answer answer = new Answer();
                answer.setName(finalArray[j]);
                answerList.add(answer);
            }
            q.setSize(finalArray.length - 2);
            newMap.put(q, answerList);
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
}
