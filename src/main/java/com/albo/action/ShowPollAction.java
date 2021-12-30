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

public class ShowPollAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowPollAction.class);
    private static final String ATTRIBUTE_COUNT_OF_QUESTIONS = "count";
    private static final String POLL_PAGE = "poll";
    private static final String START_PAGE = "main";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.trace("create page for poll");
        int id = Integer.parseInt(req.getParameter("id"));
        try{
            Poll poll = getPollById(id);
            int[] arrayForCountOfQuestions = new int[poll.getListOfQuestions().size()];
            for (int i = 0; i < poll.getListOfQuestions().size(); i++) {
                arrayForCountOfQuestions[i] = i + 1;
            }
            req.setAttribute(ATTRIBUTE_OF_POLL,poll);
            req.setAttribute(ATTRIBUTE_COUNT_OF_QUESTIONS,arrayForCountOfQuestions);
            return POLL_PAGE;
        } catch (ServiceNoDataException e){
            log.trace("Can't show poll");
            return START_PAGE;
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
