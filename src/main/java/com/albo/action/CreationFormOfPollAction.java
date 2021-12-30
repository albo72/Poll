package com.albo.action;

import com.albo.exception.ActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreationFormOfPollAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(CreationFormOfPollAction.class);
    private static final String POLL_CREATION_FORM_PAGE = "poll-creation";
    private static final String COUNT_OF_QUESTIONS_PAGE = "pageForCountOfQuestions";
    private static final String ATTRIBUTE_FOR_COUNT_OF_QUESTIONS = "countOfQuestions";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.info("starting to create form for poll creation");
        int countOfQuestions = Integer.parseInt(req.getParameter("countOfQuestions"));
        try{
            if(countOfQuestions < 1 || countOfQuestions > 20){
                return COUNT_OF_QUESTIONS_PAGE;
            }
            int[] arrayForCount = new int[countOfQuestions];
            for (int i = 0; i < countOfQuestions; i++) {
                arrayForCount[i] = i + 1;
            }
            req.setAttribute(ATTRIBUTE_FOR_COUNT_OF_QUESTIONS,arrayForCount);
        } catch (Exception e){
            log.trace("error");
            throw new ActionException(e);
        }
        return POLL_CREATION_FORM_PAGE;
    }
}
