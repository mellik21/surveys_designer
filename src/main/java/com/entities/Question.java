package com.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question", schema = "surveys")
public class Question {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String  name;
    @Column(name = "type")
    private String type;
    @Column(name = "size")
    private int size;
    @Column(name="questionnaire_id")
    private int questionnaire_id;
    @Column(name="number")
    private int number;
    
    public Question() {
    }

    public Question(String name, String type, int size, int number) {
        super();
        this.name=name;
        this.type=type;
        this.size=size;
        this.number=number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(int questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    @Override
    public String toString(){
        return id + " " +
                name + " " +
                type + " " +
                size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void merge(Question other){
        setName(other.name);
        setNumber(other.number);
        setQuestionnaire_id(other.questionnaire_id);
        setType(other.type);
    }


}
