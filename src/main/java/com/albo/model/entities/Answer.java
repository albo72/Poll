package com.albo.model.entities;

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

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public Answer setId(int id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Answer setUser(User user) {
        this.user = user;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Answer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Answer setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
