package com.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questionnaire", schema = "surveys")
public class Questionnaire {
    @Id
    @Column(name = "questionnaire_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "size")
    private int size;
    @Column(name = "description")
    private String description;
    private int numberOfAnswers;

    @OneToMany(mappedBy="questionnaire", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Question> questions;


    public Questionnaire() {

    }

    public Questionnaire(String title, String description, int user_id, int size) {
        super();
        this.title = title;
        this.userId = user_id;
        this.size = size;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + userId+" "+questions.toString();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}