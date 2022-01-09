package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.UserNotFoundException;
import com.albo.model.entities.User;
import com.albo.model.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ShowPageForUsersAnswersAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowPageForUsersAnswersAction.class);
    private static final UserService userService = new UserService();

    private static final String ATTRIBUTE_LIST_OF_USERS = "users";
    private static final String ATTRIBUTE_POLL_ID = "id";
    private static final String USERS_PAGE = "users-for-answers";
    private static final String START_PAGE = "main";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.debug("create page for users who passed poll");
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            List<User> users = userService.getListOfUsersByPollId(id);
            req.setAttribute(ATTRIBUTE_POLL_ID, id);
            req.setAttribute(ATTRIBUTE_LIST_OF_USERS, users);
            return USERS_PAGE;
        } catch (UserNotFoundException e) {
            log.debug("no users who passed the survey were found. Service exception", e);
            return USERS_PAGE;
        } catch (ServiceException e) {
            log.error("Error. Server exception", e);
            return START_PAGE;
        }
    }
}
