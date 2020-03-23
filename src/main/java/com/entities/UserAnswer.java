package com.entities;

import javax.persistence.*;

@Entity
@Table(name = "useranswer", schema = "surveys")
public class UserAnswer {
    @Id
    @Column(name = "user_answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    private String  value;
    @Column(name = "question_id")
    private int question_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "questionnaire_id")
    private int questionnaire_id;

    public UserAnswer() {
    }

    public UserAnswer(String value, int question_id, int questionnaire_id, int user_id) {
        super();
        this.value=value;
        this.question_id=question_id;
        this.questionnaire_id = questionnaire_id;
        this.user_id = user_id;
    }


    @Override
    public String toString(){
        return question_id+" "+value+" "+user_id+" "+questionnaire_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(int questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }
}
