package com.albo.action;

import com.albo.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageAction implements Action{

    private String pageAction;

    public ShowPageAction(String pageAction) {
        this.pageAction = pageAction;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        return pageAction;
    }
}
