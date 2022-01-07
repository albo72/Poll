package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.entities.Poll;
import com.albo.model.services.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;


public class ActivePollsAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ActivePollsAction.class);
    private static final PollService pollService = new PollService();
    private static final String ATTRIBUTE_OF_ACTIVE_POLLS = "polls";
    private static final String ACTIVE_POLLS_PAGE = "polls-list";
    private static final String MAIN_PAGE = "main";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.debug("create list of active polls");
        try {
            List<Poll> listOfActivePolls = pollService.getActivePolls();
            req.setAttribute(ATTRIBUTE_OF_ACTIVE_POLLS, listOfActivePolls);
            return ACTIVE_POLLS_PAGE;
        } catch (ServiceNoDataException e) {
            log.error("There are no active polls", e);
            return MAIN_PAGE;
        } catch (ServiceException e) {
            log.error("Error. Can't show active polls. Server exception", e);
            throw new ActionException(e);
        }
    }
}
