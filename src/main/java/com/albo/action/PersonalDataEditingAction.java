package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.model.entities.User;
import com.albo.model.services.UserService;
import com.albo.dto.EditionUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

import static com.albo.util.ConstantHolder.ATTRIBUTE_SESSION_USER;

public class PersonalDataEditingAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(PersonalDataEditingAction.class);
    private final UserService userService = new UserService();

    private static final String EDITING_SUCCESS = "personal-data";
    private static final String FORM_PAGE = "personal-data-editing";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String LAST_NAME_PARAMETER = "last_name";
    private static final String EMAIL_PARAMETER = "email";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.trace("Can't change encoding");
        }
        log.trace("start editing personal data");
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute(ATTRIBUTE_SESSION_USER);
        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        String firstName = req.getParameter(FIRST_NAME_PARAMETER);
        String lastName = req.getParameter(LAST_NAME_PARAMETER);
        String email = req.getParameter(EMAIL_PARAMETER);
        if (login.equals("") && password.equals("") && email.equals("")) {
            log.trace("form's parameters are not valid");
            return FORM_PAGE;
        }
        EditionUserDTO editionUserDTO = new EditionUserDTO(user.getId(),login,password,user.getIsAdmin(),firstName,
                lastName,email,user.getDateJoined());
        try {
            User updatedUser = userService.updateUser(editionUserDTO);
            session.setAttribute(ATTRIBUTE_SESSION_USER,updatedUser);
            log.trace("editing completed");
            return EDITING_SUCCESS;
        } catch (ServiceException e) {
            log.trace("Error. Can't update this user");
            return FORM_PAGE;
        }
    }
}
