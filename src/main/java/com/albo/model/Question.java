package com.albo.model;

public class Question {
    private int id;
    private String questionName;

    public Question(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public Question(String questionName) {
        this.questionName = questionName;
    }

    public int getId() {
        return id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
}
