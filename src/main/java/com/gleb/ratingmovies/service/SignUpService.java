package com.gleb.ratingmovies.service;


import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.impl.UserDaoImpl;
import com.gleb.ratingmovies.exception.DaoException;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.validator.impl.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignUpService {

    private static final String SIGN_UP_PROBLEM = "Error sign-up";
    private static final String EXIST_PROBLEM_LOGIN = "Login exist";
    private static final String EXIST_PROBLEM_EMAIL = "Email exist";
    private static final String EXIST_PROBLEM_TELEGRAM = "Telegram exist";
    private static final UserValidator userValidator = UserValidator.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();

    private static SignUpService instance;

    private SignUpService() {
    }

    public static SignUpService getInstance() {
        if (instance == null) {
            instance = new SignUpService();
        }
        return instance;
    }


    public boolean isValid(String login, String email, String telegram, String password, String name) {
        return userValidator.isValid(login, email, telegram, password, name);
    }

    public long signUp(User user) throws ServiceException {
        long userId = 1;
        try {
            userDao.save(user);
        } catch (DaoException e) {
            logger.error(SIGN_UP_PROBLEM + e);
            throw new ServiceException(SIGN_UP_PROBLEM + e);

        }

        return userId;
    }

    public boolean isUserLoginExist(String login) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.warn(EXIST_PROBLEM_LOGIN);
            throw new ServiceException(EXIST_PROBLEM_LOGIN + e);

        }
        if (user.isPresent()) {
            logger.warn(EXIST_PROBLEM_LOGIN);
            return true;
        } else {
            return false;
        }

    }


    public boolean isUserEmailExist(String email) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findUserByEmail(email);
        } catch (DaoException e) {
            logger.warn(EXIST_PROBLEM_EMAIL);
            throw new ServiceException(EXIST_PROBLEM_EMAIL + e);
        }
        if (user.isPresent()) {
            logger.warn(EXIST_PROBLEM_EMAIL);
            return true;
        } else {
            return false;
        }

    }

    public boolean isUserTelegramExist(String telegram) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findUserByTelegram(telegram);
        } catch (DaoException e) {
            logger.warn(EXIST_PROBLEM_TELEGRAM);
            throw new ServiceException(EXIST_PROBLEM_TELEGRAM + e);
        }
        if (user.isPresent()) {
            logger.warn(EXIST_PROBLEM_TELEGRAM);
            return true;
        } else {
            return false;
        }

    }
}
