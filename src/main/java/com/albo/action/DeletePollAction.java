package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.model.services.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.albo.util.ConstantHolder.REDIRECT;
import static com.albo.util.ConstantHolder.END_URL;

public class DeletePollAction implements Action{

    private static final Logger log = LoggerFactory.getLogger(DeletePollAction.class);
    private static final String POLL_ID_PARAMETER = "id";
    private static final String PREVIOUS_PAGE_ACTION = "activePolls";
    private static final PollService pollService = new PollService();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int id = Integer.parseInt(req.getParameter(POLL_ID_PARAMETER));
        log.info("start deleting poll with id {}",id);
        try {
            pollService.deletePollById(id);
            log.info("poll with id {} was deleted",id);
            return REDIRECT + PREVIOUS_PAGE_ACTION + END_URL;
        } catch (ServiceException e) {
            log.trace("Error. Can't delete this poll");
            throw new ActionException(e);
        }

    }
}
