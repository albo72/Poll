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

import static com.albo.util.ConstantHolder.ATTRIBUTE_OF_POLL;

public class ShowPollAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowPollAction.class);
    private static final PollService pollService = new PollService();
    private static final String ATTRIBUTE_COUNT_OF_QUESTIONS = "count";
    private static final String POLL_PAGE = "poll";
    private static final String START_PAGE = "main";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.trace("create page for poll");
        int id = Integer.parseInt(req.getParameter("id"));
        try{
            Poll poll = pollService.getPollById(id);
            int[] arrayForCountOfQuestions = new int[poll.getListOfQuestions().size()];
            for (int i = 0; i < poll.getListOfQuestions().size(); i++) {
                arrayForCountOfQuestions[i] = i + 1;
            }
            req.setAttribute(ATTRIBUTE_OF_POLL,poll);
            req.setAttribute(ATTRIBUTE_COUNT_OF_QUESTIONS,arrayForCountOfQuestions);
            return POLL_PAGE;
        } catch (ServiceNoDataException e){
            log.error("Can't show poll", e);
            return START_PAGE;
        } catch (ServiceException e) {
            log.error("Error. Server exception", e);
            throw new ActionException(e);
        }
    }
}
