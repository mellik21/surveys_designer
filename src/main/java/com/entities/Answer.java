package com.entities;

import javax.persistence.*;
@Entity
@Table(name = "answer", schema = "surveys")
public class Answer {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String  name;
    @Column(name = "question_id")
    private int questionId;
    @Column(name="number")
    private int number;

    @ManyToOne
    @JoinColumn(name="question_id",insertable = false, updatable = false)
    private Question question;

    public Answer() {
    }

    public Answer(String name, int question_id, int number) {
        super();
        this.name=name;
        this.questionId =question_id;
        this.number=number;
    }
    public int getId(){
        return this.id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int question_id) {
        this.questionId = question_id;
    }

    @Override
    public String toString(){
        return id+" "+name+" "+ questionId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setId(int id) {
        this.id = id;
    }
}
