package com.albo.model.services;

import com.albo.exception.AnswersNotFoundException;
import com.albo.exception.ServiceException;
import com.albo.model.entities.Answer;
import com.albo.model.builders.AnswerBuilder;
import com.albo.model.entities.Question;
import com.albo.model.entities.User;
import com.albo.model.dao.*;
import com.albo.dto.AnswerDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AnswerService {

    public void saveAnswer(AnswerDTO answerDTO) throws ServiceException {
        LocalDateTime date = LocalDateTime.now();
        try (DaoFactory factory = DaoFactory.createFactory()) {
            AnswerDao answerDao = factory.getAnswerDao();
            User user = answerDTO.getUser();
            Question question = answerDTO.getQuestion();
            String answerName = answerDTO.getAnswer();
            Answer answer = new AnswerBuilder().withUser(user).withQuestion(question).withAnswer(answerName).
                    withDate(date).build();
            answerDao.create(answer);
        } catch (DaoException e) {
            throw new ServiceException("Can't save answer. Service exception", e);
        }
    }

    public Map<Question,List<Answer>> getMapOfAnswersOnQuestionsByUserId(int userId) throws ServiceException, AnswersNotFoundException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            AnswerDao answerDao = factory.getAnswerDao();
            List<Answer> answers = answerDao.getListOfAnswersByUserId(userId);
            if (answers.size() == 0) {
                throw new AnswersNotFoundException("Answers not found");
            }
            return answers.stream().collect(Collectors.groupingBy(Answer::getQuestion, () ->
                    new TreeMap<>(Comparator.comparing(Question::getId)), Collectors.toList()));
        } catch (DaoException e) {
            throw new ServiceException("Can't get answers. Service exception", e);
        }
    }
}
