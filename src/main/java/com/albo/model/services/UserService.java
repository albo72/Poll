package com.albo.model.services;

import com.albo.exception.DaoNoDataException;
import com.albo.exception.ServiceException;
import com.albo.exception.UserNotFoundException;
import com.albo.model.User;
import com.albo.model.dao.DaoException;
import com.albo.model.dao.DaoFactory;
import com.albo.model.dao.UserDao;
import com.albo.dto.EditionUserDTO;
import com.albo.dto.RegistrationDTO;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public User getUserByLogin(String login) throws Exception {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.getByLogin(login);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public User getUserByLoginAndPassword(String login, String password) throws ServiceException, UserNotFoundException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            UserDao userDao = factory.getUserDao();
            User user = userDao.getByLoginAndPassword(login, password);
            if (user == null) {
                throw new DaoNoDataException("user not found");
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (DaoNoDataException e) {
            throw new UserNotFoundException(e);
        }
    }

    public List<User> getListOfUsersByPollId(int pollId) throws UserNotFoundException, ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            UserDao userDao = factory.getUserDao();
            List<User> users = userDao.getListOfUsersByPollId(pollId);
            if (users.size() == 0) {
                throw new DaoNoDataException("no users who passed the poll were found. Service exception");
            }
            int previousUserId = 0;
            int currentUserId;
            List<User> listOfUsers = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                currentUserId = users.get(i).getId();
                if(currentUserId!=previousUserId){
                    listOfUsers.add(users.get(i));
                }
                previousUserId = currentUserId;
            }
            return listOfUsers;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (DaoNoDataException e) {
            throw new UserNotFoundException(e);
        }
    }

    public User signUp(RegistrationDTO input) throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            UserDao userDao = factory.getUserDao();
            String login = input.getLogin();
            String password = input.getPassword();
            String firstName = input.getFirstName();
            String lastName = input.getLastName();
            String email = input.getEmail();
            LocalDateTime date = LocalDateTime.now();
            return userDao.save(new User(login, password, false, firstName, lastName, email, date));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public User saveAnonymousUser() throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            LocalDateTime date = LocalDateTime.now();
            UserDao userDao = factory.getUserDao();
            String passwordForAnonymousUser = "anonym";
            String hashedPassword = DigestUtils.sha256Hex(passwordForAnonymousUser);
            return userDao.saveAnonymousUser(new User(hashedPassword, date));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public User updateUser(EditionUserDTO input) throws ServiceException {
        try (DaoFactory factory = DaoFactory.createFactory()) {
            UserDao userDao = factory.getUserDao();
            int id = input.getId();
            String login = input.getLogin();
            String password = input.getPassword();
            boolean isAdmin = input.isAdmin();
            String firstName = input.getFirstName();
            String lastName = input.getLastName();
            String email = input.getEmail();
            LocalDateTime date = input.getDateJoined();
            User user = new User(id, login, password, isAdmin, firstName, lastName, email, date);
            userDao.update(user);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
