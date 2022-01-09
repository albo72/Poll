package com.albo.model.services;

import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.entities.Poll;
import com.albo.model.builders.PollBuilder;
import com.albo.model.dao.*;
import com.albo.dto.PollDTO;

import java.time.LocalDateTime;
import java.util.List;


public class PollService {

    private final QuestionService questionService = new QuestionService();

    public void createPoll(PollDTO pollDTO) throws ServiceException {
        LocalDateTime dateStart = LocalDateTime.now();
        Poll poll = new PollBuilder().withName(pollDTO.getName()).withDescription(pollDTO.getDescription()).
                withDateStart(dateStart).withDateEnd(pollDTO.getDateEnd()).withActivity(pollDTO.isActive()).
                withQuestions(pollDTO.getQuestions()).build();
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            poll = pollDao.createAndGet(poll);
            questionService.createListOfQuestions(pollDTO.getQuestions(), poll);
        } catch (DaoException e) {
            throw new ServiceException("can't create poll. service exception", e);
        }
    }

    public Poll getPollById(int id) throws ServiceException, ServiceNoDataException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            Poll poll = pollDao.getBy(id);
            if(poll == null){
                throw new ServiceNoDataException("Haven't poll with this id");
            }
            return poll;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Poll> getActivePolls() throws ServiceException, ServiceNoDataException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            LocalDateTime date = LocalDateTime.now();
            List<Poll> polls = pollDao.getActivePolls();
            if (polls.size() == 0) {
                throw new ServiceNoDataException("There are no active polls");
            } else {
                for (Poll poll : polls) {
                    if (poll.getDateEnd().isBefore(date)) {
                        pollDao.updateActivity(poll, false);
                    }
                }
            }
            return polls;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Poll> getInactivePolls() throws ServiceException, ServiceNoDataException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            List<Poll> polls = pollDao.getInactivePolls();
            if (polls.size() == 0) {
                throw new ServiceNoDataException("There are no inactive polls");
            }
            return polls;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Poll> getAllPolls() throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            return pollDao.getAllPolls();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Poll> getAllPollsWithLimitAndOffset(int limit, int offset) throws ServiceException, ServiceNoDataException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            LocalDateTime date = LocalDateTime.now();
            List<Poll> polls = pollDao.getAllPollsWithLimitAndOffset(limit, offset);
            if (polls.size() == 0) {
                throw new ServiceNoDataException("No polls. Service exception");
            } else {
                for (Poll poll : polls) {
                    if (poll.getDateEnd().isBefore(date) && poll.isActivity()) {
                        pollDao.updateActivity(poll, false);
                    }
                }
            }
            return polls;
        } catch (DaoException e) {
            throw new ServiceException("Can't get polls. Service exception", e);
        }
    }

    public void deletePollById(int pollId) throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            questionService.deleteQuestionsByPollId(pollId);
            pollDao.deleteBy(pollId);
        } catch (DaoException e) {
            throw new ServiceException("can't delete poll. service exception", e);
        }
    }

    public int getCountOfAllPolls() throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            return pollDao.getCountOfAllPolls();
        } catch (DaoException e) {
            throw new ServiceException("Can't get count off polls. Service exception", e);
        }
    }

}
