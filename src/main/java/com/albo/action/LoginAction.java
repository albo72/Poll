package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.UserNotFoundException;
import com.albo.model.User;
import com.albo.model.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.albo.util.ConstantHolder.*;

public class LoginAction implements Action {

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
            User user = checkUser(login, hashedPassword);
            HttpSession session = req.getSession(true);
            session.setAttribute(ATTRIBUTE_SESSION_USER, user);
            log.info("user with id {} is logged in",user.getId());
            return LOGIN_SUCCESS;
        } catch (UserNotFoundException e) {
            log.trace("User not found with login {}", login, e);
            req.setAttribute("incorrect", INCORRECT_DATA);
            return FORM_NAME;
        }
    }

    private User checkUser(String login, String password) throws UserNotFoundException {
        UserService userService = new UserService();
        try {
            return userService.getUserByLoginAndPassword(login, password);
        } catch (ServiceException e) {
            throw new UserNotFoundException(e);
        }
    }
}
