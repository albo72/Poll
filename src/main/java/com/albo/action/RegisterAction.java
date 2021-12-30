package com.albo.action;

import com.albo.exception.ActionException;
import com.albo.exception.ServiceException;
import com.albo.model.User;
import com.albo.model.services.UserService;
import com.albo.dto.RegistrationDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;

import static com.albo.util.ConstantHolder.ATTRIBUTE_SESSION_USER;

public class RegisterAction implements Action{

    private static final Logger log = LoggerFactory.getLogger(RegisterAction.class);
    private static final UserService userService = new UserService();

    private static final String FORM_NAME = "register";
    private static final String REGISTER_SUCCESS = "main";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String LAST_NAME_PARAMETER = "last_name";
    private static final String EMAIL_PARAMETER = "email";



    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        log.trace("start to register user");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.trace("Can't change encoding");
        }
        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        String firstName = req.getParameter(FIRST_NAME_PARAMETER);
        String lastName = req.getParameter(LAST_NAME_PARAMETER);
        String email = req.getParameter(EMAIL_PARAMETER);

        String hashedPassword = DigestUtils.sha256Hex(password);
        try{
            if(login.equals("") && password.equals("") && email.equals("")){
                log.trace("register form's parameters are not valid");
                return FORM_NAME;
            }
        }catch (Exception e){
            throw new ActionException(e);
        }

        RegistrationDTO registrationDTO = new RegistrationDTO(login, hashedPassword, firstName, lastName, email);
        try {
            User user = userService.signUp(registrationDTO);
            log.trace("user {} was registered successfully", registrationDTO.getLogin());
            HttpSession session = req.getSession(true);
            session.setAttribute(ATTRIBUTE_SESSION_USER, user);
            return REGISTER_SUCCESS;
        } catch (ServiceException e) {
            log.trace("user registration was failed");
            return FORM_NAME;
        }
    }
}
