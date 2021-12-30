package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.Poll;
import com.albo.model.services.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.albo.util.ConstantHolder.ATTRIBUTE_OF_POLL;

public class ShowFormForAnswers implements Action{

    private static final Logger log = LoggerFactory.getLogger(ShowFormForAnswers.class);
    private static final String FORM_PAGE = "poll-passing";
    private static final String POLL_ID_PARAMETER = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.trace("create page for passing poll");
        int id = Integer.parseInt(req.getParameter(POLL_ID_PARAMETER));
        try{
            Poll poll = getPollById(id);
            req.setAttribute(ATTRIBUTE_OF_POLL,poll);
            return FORM_PAGE;
        } catch (ServiceNoDataException e){
            log.trace("Can't show poll");
            return "main";
        }
    }

    private Poll getPollById(int id) throws ServiceNoDataException, ActionException {
        PollService pollService = new PollService();
        try {
            return pollService.getPollById(id);
        } catch (ServiceException e){
            throw new ActionException(e);
        }
    }
}
