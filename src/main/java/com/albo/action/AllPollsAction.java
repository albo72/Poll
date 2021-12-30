package com.albo.action;

import com.albo.Paginator;
import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.exception.ServiceNoDataException;
import com.albo.model.Poll;
import com.albo.model.services.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllPollsAction implements Action{
    private static final Logger log = LoggerFactory.getLogger(AllPollsAction.class);
    private static final PollService pollService = new PollService();
    private static final String ATTRIBUTE_ALL_POLLS = "polls";
    private static final String ATTRIBUTE_LIST_OF_PAGES = "listOfPages";
    private static final String ATTRIBUTE_PAGE = "page";
    private static final String ALL_POLLS_PAGE = "polls-list";
    private static final String MAIN_PAGE = "main";

    private static final int LIMIT = 1;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        log.debug("create list of polls");
        int currentPage = Integer.parseInt(req.getParameter("page"));
        try{
            int countOfElements = pollService.getCountOfAllPolls();
            Paginator paginator = new Paginator();
            List<Integer> listOfPages = paginator.getListOfPages(currentPage,paginator.getCountOfPages(LIMIT,countOfElements));
            List<Poll> listOfPolls = pollService.getAllPollsWithLimitAndOffset(LIMIT,paginator.getOffset(currentPage,LIMIT));
            req.setAttribute(ATTRIBUTE_PAGE,currentPage);
            req.setAttribute(ATTRIBUTE_ALL_POLLS, listOfPolls);
            req.setAttribute(ATTRIBUTE_LIST_OF_PAGES,listOfPages);
            return ALL_POLLS_PAGE;
        } catch (ServiceException e) {
            log.error("Error. Server exception",e);
            throw new ActionException(e);
        } catch (ServiceNoDataException e) {
            log.debug("No polls, sorry",e);
            return MAIN_PAGE;
        }
    }


}
