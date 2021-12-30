package com.albo.model.dao;

import com.albo.model.Answer;

import java.util.List;

public interface AnswerDao {

    void create(Answer answer);

    Answer getBy(int id);

    List<Answer> getListOfAnswersByUserId(int userId) throws DaoException;

}
