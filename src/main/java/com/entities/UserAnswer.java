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
    private int questionId;
    @Column(name = "questionnaire_id")
    private int questionnaireId;


    @ManyToOne
    @JoinColumn(name="question_id",insertable = false, updatable = false)
    private Question question;


    public UserAnswer() {
    }

    public UserAnswer(String value, int question_id, int questionnaire_id) {
        super();
        this.value=value;
        this.questionId =question_id;
        this.questionnaireId = questionnaire_id;
    }


    @Override
    public String toString(){
        return questionId +" "+value+" "+ questionnaireId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int question_id) {
        this.questionId = question_id;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaire_id) {
        this.questionnaireId = questionnaire_id;
    }
}
