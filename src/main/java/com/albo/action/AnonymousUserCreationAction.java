package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.model.entities.User;
import com.albo.model.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.albo.util.ConstantHolder.ATTRIBUTE_SESSION_USER;
import static com.albo.util.ConstantHolder.REDIRECT;

public class AnonymousUserCreationAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(AnonymousUserCreationAction.class);
    private static final UserService userService = new UserService();

    private static final String POLL_ID_PARAMETER = "id";
    private static final String NEXT_PAGE_NAME_WITH_ID_PARAMETER = "pollPassing.do?id=";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.debug("start to create anonymous user");
        int pollId = Integer.parseInt(req.getParameter(POLL_ID_PARAMETER));
        User user = null;
        try {
            user = userService.saveAnonymousUser();
            HttpSession session = req.getSession(true);
            session.setAttribute(ATTRIBUTE_SESSION_USER, user);
            return REDIRECT + NEXT_PAGE_NAME_WITH_ID_PARAMETER + pollId;
        } catch (ServiceException e) {
            log.error("Can't save anonymous user", e);
            throw new ActionException(e);
        }

    }

}
