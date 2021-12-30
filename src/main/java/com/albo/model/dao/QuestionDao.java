package com.albo.model.dao;

import com.albo.model.Poll;
import com.albo.model.Question;

import java.util.List;

public interface QuestionDao {

    void create(Question question, Poll poll) throws DaoException;

    void createListOfQuestions(List<Question> questions, Poll poll) throws DaoException;

    void update(Question question, Poll poll);

    void updateListOfQuestions(List<Question> questions, Poll poll);

    Question getBy(int id);

    void deleteBy(int id);

    void deleteByPollId(int pollId) throws DaoException;
}
