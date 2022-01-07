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

public class InactivePollsAction implements Action{

    private static final Logger log = LoggerFactory.getLogger(InactivePollsAction.class);
    private static final PollService pollService = new PollService();
    private static final String ATTRIBUTE_OF_INACTIVE_POLLS = "polls";
    private static final String INACTIVE_POLLS_PAGE = "polls-list";
    private static final String MAIN_PAGE = "main";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.trace("create list of inactive polls");
        try{
            List<Poll> listOfInactivePolls = getInactivePolls();
            req.setAttribute(ATTRIBUTE_OF_INACTIVE_POLLS, listOfInactivePolls);
            return INACTIVE_POLLS_PAGE;
        } catch (ServiceNoDataException e){
            log.trace("Can't show inactive polls");
            return MAIN_PAGE;
        }
    }

    private List<Poll> getInactivePolls() throws ServiceNoDataException {
        try {
            return pollService.getInactivePolls();
        } catch (ServiceException e){
            throw new ServiceNoDataException(e);
        }
    }
}
