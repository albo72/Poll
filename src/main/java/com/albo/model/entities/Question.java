package com.albo.model.entities;

import java.util.Objects;

public class Question {
    private int id;
    private String questionName;

    public Question(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                Objects.equals(questionName, question.questionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionName);
    }
}
