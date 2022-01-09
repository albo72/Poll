package com.albo.model.dao;

import com.albo.exception.JdbcException;
import com.albo.model.entities.Poll;
import com.albo.model.entities.Question;

import java.util.List;

public interface QuestionDao {

    void create(Question question, Poll poll) throws DaoException;

    void createListOfQuestions(List<Question> questions, Poll poll) throws DaoException;

    void update(Question question, Poll poll) throws JdbcException;

    Question getBy(int id) throws JdbcException;

    void deleteBy(int id) throws JdbcException;

    void deleteByPollId(int pollId) throws DaoException;
}
