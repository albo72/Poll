package com.albo.dto;

import com.albo.model.entities.Question;
import com.albo.model.entities.User;

public class AnswerDTO {
    private User user;
    private Question question;
    private String answer;

    public AnswerDTO(User user, Question question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
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
}
