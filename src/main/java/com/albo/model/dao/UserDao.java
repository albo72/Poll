package com.albo.model.dao;

import com.albo.exception.DaoNoDataException;
import com.albo.exception.JdbcException;
import com.albo.model.entities.User;

import java.util.List;

public interface UserDao {

    User save(User user) throws DaoException;

    User saveAnonymousUser(User user) throws DaoException;

    void update(User user) throws JdbcException;

    User getById(int id) throws JdbcException;

    User getByLogin(String login) throws DaoException;

    User getByLoginAndPassword(String login, String password) throws DaoException, DaoNoDataException;

    List<User> getListOfUsersByPollId(int pollId) throws DaoException;
}
