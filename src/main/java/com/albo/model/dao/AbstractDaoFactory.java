package com.albo.model.dao;

import com.albo.exception.JdbcException;
import com.albo.model.dao.jdbc.JdbcDaoFactory;

public class AbstractDaoFactory {
    private static DaoFactory jdbcDaoFactory = null;

    static {
        try {
            jdbcDaoFactory = new JdbcDaoFactory();
        } catch (JdbcException e) {
            e.printStackTrace();
        }
    }

    public static UserDao getUserDao() {
        return jdbcDaoFactory.getUserDao();
    }

    public static PollDao getPollDao() {
        return jdbcDaoFactory.getPollDao();
    }

    public static QuestionDao getQuestionDao() {
        return jdbcDaoFactory.getQuestionDao();
    }

    public static AnswerDao getAnswerDao() {
        return jdbcDaoFactory.getAnswerDao();
    }
}
