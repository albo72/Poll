package com.albo.dto;

import com.albo.model.entities.Question;

import java.time.LocalDateTime;
import java.util.List;

public class PollDTO {
    private String name;
    private String description;
    private LocalDateTime dateEnd;
    private boolean isActive = true;
    private List<Question> questions;

    public PollDTO(String name, String description, LocalDateTime dateEnd, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.dateEnd = dateEnd;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
