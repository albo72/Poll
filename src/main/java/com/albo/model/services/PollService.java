package com.albo.model.services;

import com.albo.exception.DaoNoDataException;
import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.Poll;
import com.albo.model.dao.*;
import com.albo.dto.PollDTO;

import java.time.LocalDateTime;
import java.util.List;


public class PollService {

    private final PollDao pollDao = AbstractDaoFactory.getPollDao();
    private final QuestionService questionService = new QuestionService();

    public void createPoll(PollDTO pollDTO) throws ServiceException {
        LocalDateTime dateStart = LocalDateTime.now();
        //LocalDateTime dateEnd = this.getTimeEnd(dateStart, pollDTO);
        Poll poll = new Poll(pollDTO.getName(), pollDTO.getDescription(), dateStart, pollDTO.getDateEnd(),
                pollDTO.isActive(), pollDTO.getQuestions());
        try {
            poll = pollDao.createAndGet(poll);
            questionService.createListOfQuestions(pollDTO.getQuestions(), poll);
        } catch (DaoException e) {
            throw new ServiceException("can't create poll. service exception");
        }
    }

    /*public void updatePoll(PollDTO pollDTO) {
        LocalDateTime dateStart = LocalDateTime.now();
        LocalDateTime dateEnd = this.getTimeEnd(dateStart, pollDTO);
        pollDao.update(new Poll(pollDTO.getName(), pollDTO.getDescription(), dateStart, dateEnd, pollDTO.isActive(),
                pollDTO.getQuestions()));
    }
*/
    public Poll getPollById(int id) throws ServiceException, ServiceNoDataException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            return pollDao.getBy(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (DaoNoDataException e) {
            throw new ServiceNoDataException(e);
        }
    }

    public List<Poll> getActivePolls() throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            return pollDao.getActivePolls();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Poll> getInactivePolls() throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            PollDao pollDao = factory.getPollDao();
            return pollDao.getInactivePolls();
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
            List<Poll> polls = pollDao.getAllPollsWithLimitAndOffset(limit, offset);
            if(polls.size() == 0){
                throw new ServiceNoDataException("No polls. Service exception");
            }
            return polls;
        } catch (DaoException e) {
            throw new ServiceException("Can't get polls. Service exception", e);
        }
    }

    public void deletePollById(int pollId) throws ServiceException {
        try {
            questionService.deleteQuestionsByPollId(pollId);
            pollDao.deleteBy(pollId);
        } catch (DaoException e) {
            throw new ServiceException("can't delete poll. service exception");
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

    /*private LocalDateTime getTimeEnd(LocalDateTime dateStart, PollDTO pollDTO) {
        long dateStartInMilli = dateStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return Instant.ofEpochMilli(dateStartInMilli + pollDTO.getDateEnd()).
                atZone(ZoneId.systemDefault()).toLocalDateTime();
    }*/
}
