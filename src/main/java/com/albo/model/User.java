package com.albo.model;

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

    public User(int id, String login, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String login, String password, boolean isAdmin, String firstName, String lastName, String email,
                LocalDateTime dateJoined) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    public User(String login, String password, String firstName, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String password, LocalDateTime dateJoined) {
        this.password = password;
        this.dateJoined = dateJoined;
    }

    public User(int id, String login, String firstName, String lastName, String email, LocalDateTime dateJoined) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }
}
