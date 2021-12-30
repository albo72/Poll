package com.albo.action;

import com.albo.exception.ActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.albo.util.ConstantHolder.REDIRECT;

public class LogoutAction implements Action{

    private static final Logger log = LoggerFactory.getLogger(LogoutAction.class);
    private static final String MAIN_PAGE = "/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.trace("start to logout");
        req.getSession(false).invalidate();
        log.trace("session was deleted");
        return REDIRECT + MAIN_PAGE;
    }
}
