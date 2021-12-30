package com.albo.model.services;

import com.albo.exception.AnswersNotFoundException;
import com.albo.exception.ServiceException;
import com.albo.model.Answer;
import com.albo.model.Question;
import com.albo.model.User;
import com.albo.model.dao.*;
import com.albo.dto.AnswerDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnswerService {
    AnswerDao answerDao = AbstractDaoFactory.getAnswerDao();

    public void saveAnswer(AnswerDTO answerDTO) throws ServiceException {
        LocalDateTime date = LocalDateTime.now();
        try (DaoFactory factory = DaoFactory.createFactory()) {
            AnswerDao answerDao = factory.getAnswerDao();
            User user = answerDTO.getUser();
            Question question = answerDTO.getQuestion();
            String answer = answerDTO.getAnswer();
            answerDao.create(new Answer(user, question, answer, date));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Answer> getListOfAnswersOnQuestionsByUserId(int userId) throws ServiceException, AnswersNotFoundException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            AnswerDao answerDao = factory.getAnswerDao();
            List<Answer> answers = answerDao.getListOfAnswersByUserId(userId);
            if (answers.size() == 0){
                throw new AnswersNotFoundException("Answers not found");
            }
            Map<Question, List<Answer>> collect = answers.stream().collect(Collectors.groupingBy(Answer::getQuestion));
            return answers;

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}