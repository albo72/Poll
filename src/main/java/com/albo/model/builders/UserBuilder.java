package com.albo.model.builders;

import com.albo.model.entities.User;

import java.time.LocalDateTime;

public class UserBuilder {

    private User newUser;

    public UserBuilder() {
        this.newUser = new User();
    }

    public UserBuilder withId(int id){
        this.newUser.setId(id);
        return this;
    }

    public UserBuilder withLogin(String login){
        this.newUser.setLogin(login);
        return this;
    }

    public UserBuilder withPassword(String password){
        this.newUser.setPassword(password);
        return this;
    }

    public UserBuilder withIsAdmin(boolean isAdmin){
        this.newUser.setAdmin(isAdmin);
        return this;
    }

    public UserBuilder withFirstName(String firstName){
        this.newUser.setFirstName(firstName);
        return this;
    }

    public UserBuilder withLastName(String lastName){
        this.newUser.setLastName(lastName);
        return this;
    }

    public UserBuilder withEmail(String email){
        this.newUser.setEmail(email);
        return this;
    }

    public UserBuilder withDateJoined(LocalDateTime date){
        this.newUser.setDateJoined(date);
        return this;
    }

    public User build(){
        return this.newUser;
    }
}
