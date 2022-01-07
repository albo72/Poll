package com.albo.model.builders;

import com.albo.model.entities.Answer;
import com.albo.model.entities.Question;
import com.albo.model.entities.User;

import java.time.LocalDateTime;

public class AnswerBuilder {
    private Answer newAnswer;

    public AnswerBuilder() {
        this.newAnswer = new Answer();
    }

    public AnswerBuilder withId(int id) {
        this.newAnswer.setId(id);
        return this;
    }

    public AnswerBuilder withUser(User user){
        this.newAnswer.setUser(user);
        return this;
    }

    public AnswerBuilder withQuestion(Question question){
        this.newAnswer.setQuestion(question);
        return this;
    }

    public AnswerBuilder withAnswer(String answer){
        this.newAnswer.setAnswer(answer);
        return this;
    }

    public AnswerBuilder withDate(LocalDateTime date){
        this.newAnswer.setDate(date);
        return this;
    }

    public Answer build(){
        return this.newAnswer;
    }
}
