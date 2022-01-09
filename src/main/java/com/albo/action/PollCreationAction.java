package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.model.builders.QuestionBuilder;
import com.albo.model.services.PollService;
import com.albo.model.entities.Question;
import com.albo.dto.PollDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PollCreationAction implements Action{

    private static final Logger log = LoggerFactory.getLogger(PollCreationAction.class);
    private static final String POLL_CREATION_FORM_PAGE = "poll-creation";
    private static final String POLL_CREATION_SUCCESS = "main";
    private static final String POLL_NAME_PARAMETER = "name";
    private static final String POLL_DESCRIPTION_PARAMETER = "description";
    private static final String POLL_DATE_END_PARAMETER = "dateEnd";
    private static final String POLL_QUESTIONS_PARAMETER = "questions[]";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.debug("start to create poll");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Can't change encoding", e);
        }
        String name = req.getParameter(POLL_NAME_PARAMETER);
        String description = req.getParameter(POLL_DESCRIPTION_PARAMETER);
        LocalDateTime dateEnd = LocalDateTime.parse(req.getParameter(POLL_DATE_END_PARAMETER));
        String[] arrayOfQuestions = req.getParameterValues(POLL_QUESTIONS_PARAMETER);
        List<Question> questions = new ArrayList<>();
        for (String question:arrayOfQuestions) {
            questions.add(new QuestionBuilder().withQuestionName(question).build());
        }
        PollDTO pollDTO = new PollDTO(name,description,dateEnd,questions);
        PollService pollService = new PollService();
        try {
            pollService.createPoll(pollDTO);
            log.debug("poll created");
            return POLL_CREATION_SUCCESS;
        } catch (ServiceException e) {
            log.error("poll creation was failed", e);
            return POLL_CREATION_FORM_PAGE;
        }
    }
}
