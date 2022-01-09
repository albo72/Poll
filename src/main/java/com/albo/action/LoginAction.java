package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.UserNotFoundException;
import com.albo.model.entities.User;
import com.albo.model.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.albo.util.ConstantHolder.*;

public class LoginAction implements Action {

    private static final UserService userService = new UserService();
    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
    private static final String FORM_NAME = "login";
    private static final String LOGIN_SUCCESS = "main";
    private static final String INCORRECT_DATA = "Неправильный логин или пароль";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.trace("start to authorize");

        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASS_PARAMETER);
        String hashedPassword = DigestUtils.sha256Hex(password);

        try {
            User user = userService.getUserByLoginAndPassword(login, hashedPassword);
            HttpSession session = req.getSession(true);
            session.setAttribute(ATTRIBUTE_SESSION_USER, user);
            log.debug("user with id {} is logged in", user.getId());
            return LOGIN_SUCCESS;
        } catch (UserNotFoundException e) {
            log.debug("User not found with login {}", login, e);
            req.setAttribute("incorrect", INCORRECT_DATA);
            return FORM_NAME;
        } catch (ServiceException e) {
            log.error("Error. Server exception", e);
            throw new ActionException(e);
        }
    }

}
