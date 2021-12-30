package com.albo.dto;

import java.time.LocalDateTime;

public class EditionUserDTO {
    private final int id;
    private final String login;
    private final String password;
    private final boolean isAdmin;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDateTime dateJoined;

    public EditionUserDTO(int id, String login, String password, boolean isAdmin, String firstName, String lastName, String email, LocalDateTime dateJoined) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }
}
