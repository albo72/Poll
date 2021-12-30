package com.albo.servlet;

import com.albo.action.Action;
import com.albo.action.ActionFactory;
import com.albo.exception.ActionException;
import com.albo.exception.ActionFactoryException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.albo.util.ConstantHolder.START_URL;
import static com.albo.util.ConstantHolder.END_URL;

@WebServlet(value = "*.do")
@MultipartConfig
public class MainServlet extends HttpServlet {
    private static final String VIEW_PATH = "/WEB-INF/jsp/";
    private static final String VIEW_EXTENSION = ".jsp";
    private static final String REDIRECT_PREFIX = "redirect:";

    private ActionFactory actionFactory;


    @Override
    public void init() throws ServletException {
        try {
            actionFactory = new ActionFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        int startIndex = requestURI.lastIndexOf(START_URL);
        int endIndex = requestURI.lastIndexOf(END_URL);
        String actionName = requestURI.substring(startIndex + 1, endIndex);

        try {
            Action action = actionFactory.getAction(actionName);
            String result = action.execute(req, resp);

            if (result.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(req.getContextPath() + result.substring(REDIRECT_PREFIX.length()));
            } else {
                req.getRequestDispatcher(VIEW_PATH + result + VIEW_EXTENSION).forward(req, resp);
            }

        } catch (ActionException | ActionFactoryException e) {
            throw new ServletException();
        }
    }
}
