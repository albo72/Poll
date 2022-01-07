package com.albo.model.builders;

import com.albo.model.entities.Poll;
import com.albo.model.entities.Question;

import java.time.LocalDateTime;
import java.util.List;

public class PollBuilder {

    private Poll newPoll;

    public PollBuilder() {
        this.newPoll = new Poll();
    }

    public PollBuilder withId(int id) {
        this.newPoll.setId(id);
        return this;
    }

    public PollBuilder withName(String name) {
        this.newPoll.setName(name);
        return this;
    }

    public PollBuilder withDescription(String description) {
        this.newPoll.setDescription(description);
        return this;
    }

    public PollBuilder withDateStart(LocalDateTime dateStart) {
        this.newPoll.setDateStart(dateStart);
        return this;
    }

    public PollBuilder withDateEnd(LocalDateTime dateEnd) {
        this.newPoll.setDateEnd(dateEnd);
        return this;
    }

    public PollBuilder withActivity(boolean activity) {
        this.newPoll.setActivity(activity);
        return this;
    }

    public PollBuilder withQuestions(List<Question> questions) {
        this.newPoll.setListOfQuestions(questions);
        return this;
    }

    public Poll build() {
        return this.newPoll;
    }
}
