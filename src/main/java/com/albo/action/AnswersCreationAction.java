package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.services.AnswerService;
import com.albo.model.entities.Poll;
import com.albo.model.services.PollService;
import com.albo.model.entities.User;
import com.albo.dto.AnswerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;

import static com.albo.util.ConstantHolder.ATTRIBUTE_SESSION_USER;

public class AnswersCreationAction implements Action{
    private static final AnswerService answerService = new AnswerService();
    private static final PollService pollService = new PollService();


    private static final Logger log = LoggerFactory.getLogger(AnswersCreationAction.class);
    private static final String ANSWERS_PARAMETER = "answers[]";
    private static final String POLL_ID_PARAMETER = "id";
    private static final String ANSWER_SAVING_SUCCESS = "main";
    private static final String POLL_PASSING_PAGE = "poll-passing";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.trace("Can't change encoding");
        }
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(ATTRIBUTE_SESSION_USER);
        int pollId = Integer.parseInt(req.getParameter(POLL_ID_PARAMETER));
        String[] arrayOfAnswers = req.getParameterValues(ANSWERS_PARAMETER);
        try {
            Poll poll = getPollById(pollId);
            AnswerDTO answerDTO = null;
            for (int i = 0; i < poll.getListOfQuestions().size(); i++) {
                answerDTO = new AnswerDTO(user,poll.getListOfQuestions().get(i),arrayOfAnswers[i]);
                saveAnswers(answerDTO);
            }
            log.trace("Answers saved");
        } catch (ServiceNoDataException e) {
            log.trace("Error. Can't save answers");
            return POLL_PASSING_PAGE;
        }
        return ANSWER_SAVING_SUCCESS;
    }

    private void saveAnswers(AnswerDTO answerDTO){
        try {
            answerService.saveAnswer(answerDTO);
        } catch (ServiceException e) {
            log.trace("Can't save answer");
        }

    }

    private Poll getPollById(int id) throws ServiceNoDataException, ActionException {
        try {
            return pollService.getPollById(id);
        } catch (ServiceException e){
            throw new ActionException(e);
        }
    }
}
