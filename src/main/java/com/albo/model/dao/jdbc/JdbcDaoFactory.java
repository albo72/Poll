package com.albo.model.dao.jdbc;

import com.albo.exception.JdbcException;
import com.albo.model.dao.*;

public class JdbcDaoFactory  extends DaoFactory{
    ConnectionFactory connectionFactory = new PostgresConnection();

    public JdbcDaoFactory() throws JdbcException{

    }

    @Override
    public PollDao getPollDao() {
        return new JdbcPollDao(connectionFactory);
    }

    @Override
    public QuestionDao getQuestionDao() {
        return new JdbcQuestionDao(connectionFactory);
    }

    @Override
    public AnswerDao getAnswerDao() {
        return new JdbcAnswerDao(connectionFactory);
    }

    @Override
    public UserDao getUserDao() {
        return new JdbcUserDao(connectionFactory);
    }

    @Override
    public void close() throws JdbcException {

    }
}
