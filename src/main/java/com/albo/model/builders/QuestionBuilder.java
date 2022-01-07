package com.albo.model.builders;

import com.albo.model.entities.Question;

public class QuestionBuilder {

    private Question newQuestion;

    public QuestionBuilder() {
        this.newQuestion = new Question();
    }

    public QuestionBuilder withId(int id) {
        this.newQuestion.setId(id);
        return this;
    }

    public QuestionBuilder withQuestionName(String questionName) {
        this.newQuestion.setQuestionName(questionName);
        return this;
    }

    public Question build() {
        return this.newQuestion;
    }
}
