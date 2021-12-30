package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.AnswersNotFoundException;
import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.Answer;
import com.albo.model.services.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAnswersAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowAnswersAction.class);
    private static final AnswerService answerService = new AnswerService();

    private static final String ATTRIBUTE_OF_ANSWERS = "answers";
    private static final String USER_ID_PARAMETER = "user_id";
    private static final String ANSWERS_PAGE = "answers";
    private static final String MAIN_PAGE = "main";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.debug("create list of answers");
        int userId = Integer.parseInt(req.getParameter(USER_ID_PARAMETER));
        try {
            List<Answer> answers = answerService.getListOfAnswersOnQuestionsByUserId(userId);
            req.setAttribute(ATTRIBUTE_OF_ANSWERS, answers);
            return ANSWERS_PAGE;
        } catch (ServiceException e) {
            log.error("Error. Server exception", e);
            return MAIN_PAGE;
        } catch (AnswersNotFoundException e) {
            log.debug("Answers not found", e);
            return ANSWERS_PAGE;
        }
    }
}
