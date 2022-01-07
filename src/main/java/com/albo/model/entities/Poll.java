package com.albo.model.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Poll {
    private int id;
    private String name;
    private String description;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private boolean activity;
    private List<Question> listOfQuestions;

    public Poll(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, boolean activity,
                List<Question> listOfQuestions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.activity = activity;
        this.listOfQuestions = listOfQuestions;
    }

    public Poll() {
    }

    public int getId() {
        return id;
    }

    public Poll setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Poll setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Poll setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public Poll setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public Poll setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public boolean isActivity() {
        return activity;
    }

    public Poll setActivity(boolean activity) {
        this.activity = activity;
        return this;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public Poll setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
        return this;
    }
}
