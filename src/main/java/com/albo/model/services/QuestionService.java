package com.albo.model.services;

import com.albo.exception.ServiceException;
import com.albo.model.entities.Poll;
import com.albo.model.entities.Question;
import com.albo.model.dao.DaoException;
import com.albo.model.dao.DaoFactory;
import com.albo.model.dao.QuestionDao;

import java.util.List;

public class QuestionService {

    public void createListOfQuestions(List<Question> questions, Poll poll) throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()){
            QuestionDao questionDao = factory.getQuestionDao();
            questionDao.createListOfQuestions(questions, poll);
        } catch (DaoException e) {
            throw new ServiceException("Can't create list of questions. Service exception", e);
        }
    }

    public void deleteQuestionsByPollId(int pollId) throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()){
            QuestionDao questionDao = factory.getQuestionDao();
            questionDao.deleteByPollId(pollId);
        } catch (DaoException e) {
            throw new ServiceException("Can't delete question. Service exception", e);
        }
    }
}
