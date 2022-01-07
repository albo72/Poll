package com.albo.model.dao;

import com.albo.model.entities.Poll;

import java.util.List;

public interface PollDao {

    Poll createAndGet(Poll poll) throws DaoException;

    void update(Poll poll) throws DaoException;

    void updateActivity(Poll poll, boolean activity) throws DaoException;

    Poll getBy(int id) throws DaoException;

    List<Poll> getAllPolls() throws DaoException;

    List<Poll> getAllPollsWithLimitAndOffset(int limit, int offset) throws DaoException;

    List<Poll> getActivePolls() throws DaoException;

    List<Poll> getInactivePolls() throws DaoException;

    void deleteBy(int id) throws DaoException;

    public int getCountOfAllPolls() throws DaoException;
}
