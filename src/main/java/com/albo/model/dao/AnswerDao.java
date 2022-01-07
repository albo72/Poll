package com.albo.model.dao;

import com.albo.exception.JdbcException;
import com.albo.model.entities.Answer;

import java.util.List;

public interface AnswerDao {

    void create(Answer answer) throws JdbcException;

    Answer getBy(int id) throws JdbcException;

    List<Answer> getListOfAnswersByUserId(int userId) throws DaoException;

}
