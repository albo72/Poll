package com.albo.model.dao;

import com.albo.exception.DaoNoDataException;
import com.albo.exception.JdbcException;
import com.albo.model.Poll;

import java.util.List;

public interface PollDao {
    void create(Poll poll) throws DaoException;

    Poll createAndGet(Poll poll) throws DaoException;

    void update(Poll poll);

    Poll getBy(int id) throws JdbcException, DaoNoDataException;

    List<Poll> getAllPolls() throws DaoException;

    List<Poll> getAllPollsWithLimitAndOffset(int limit, int offset) throws DaoException;

    List<Poll> getActivePolls() throws DaoException;

    List<Poll> getInactivePolls() throws DaoException;

    void deleteBy(int id) throws DaoException;

    public int getCountOfAllPolls() throws DaoException;
}
