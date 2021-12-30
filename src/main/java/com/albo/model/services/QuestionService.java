package com.albo.model.services;

import com.albo.model.Poll;
import com.albo.model.Question;
import com.albo.model.dao.AbstractDaoFactory;
import com.albo.model.dao.DaoException;
import com.albo.model.dao.QuestionDao;

import java.util.List;

public class QuestionService {
    private final QuestionDao questionDao = AbstractDaoFactory.getQuestionDao();

    public void createListOfQuestions(List<Question> questions, Poll poll) throws DaoException {
        questionDao.createListOfQuestions(questions, poll);
    }

    public void deleteQuestionsByPollId(int pollId) throws DaoException {
        questionDao.deleteByPollId(pollId);
    }
}
