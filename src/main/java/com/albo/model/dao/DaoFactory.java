package com.albo.model.dao;

import com.albo.exception.JdbcException;
import com.albo.model.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory implements AutoCloseable{

    public static DaoFactory createFactory() throws DaoException {
        try{
            return new JdbcDaoFactory();
        } catch (JdbcException ex){
            throw new DaoException(ex);
        }
    }

    @Override
    public void close() throws DaoException {

    }

    public abstract UserDao getUserDao();

    public abstract AnswerDao getAnswerDao();

    public abstract QuestionDao getQuestionDao();

    public abstract PollDao getPollDao();


}
