package com.albo.model;

import java.time.LocalDateTime;

public class Answer {
    private int id;
    private User user;
    private Question question;
    private String answer;
    private LocalDateTime date;

    public Answer(int id, User user, Question question, String answer, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public Answer(User user, Question question, String answer, LocalDateTime date) {
        this.user = user;
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public Answer(int id, String answer, LocalDateTime date) {
        this.id = id;
        this.answer = answer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
