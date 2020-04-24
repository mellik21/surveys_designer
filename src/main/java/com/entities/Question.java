package com.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private int questionnaireId;
    @Column(name="number")
    private int number;

    @OneToMany(mappedBy="question", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Answer> answers;

    @OneToMany(mappedBy="question",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<UserAnswer> userAnswers;

    @ManyToOne
    @JoinColumn(name="questionnaire_id", nullable=false, insertable = false, updatable = false)
    private Questionnaire questionnaire;

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

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaire_id) {
        this.questionnaireId = questionnaire_id;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
