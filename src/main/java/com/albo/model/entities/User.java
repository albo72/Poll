package com.albo.model.entities;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String login;
    private String password;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateJoined;

    public User(int id, String login, String password, boolean isAdmin, String firstName, String lastName, String email,
                LocalDateTime dateJoined) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public User setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public User setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
        return this;
    }
}
