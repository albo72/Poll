package com.albo.model;

import java.time.LocalDateTime;
import java.util.List;

public class Poll {
    private int id;
    private String name;
    private String description;
    private final LocalDateTime dateStart;
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

    public Poll(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, boolean activity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.activity = activity;
    }

    public Poll(String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, boolean activity) {
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.activity = activity;
    }

    public Poll(String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, boolean activity, List<Question> listOfQuestions) {
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.activity = activity;
        this.listOfQuestions = listOfQuestions;
    }

    public int getId() {
        return id;
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

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }
}
