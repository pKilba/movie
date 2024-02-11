package com.gleb.ratingmovies.service;

import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.impl.UserDaoImpl;
import com.gleb.ratingmovies.exception.DaoException;
import com.gleb.ratingmovies.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final String UPDATE_PROBLEM = "Update exception  ";
    private static final String FIND_PROBLEM = "Find exception  ";
    private static final String BLOCKED_PROBLEM = "Blocked exception  ";
    private static final Logger logger = LogManager.getLogger();
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    private UserService() {
    }

    public List findUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(FIND_PROBLEM + e);
        }
    }


    public void updateNameEmailTelegramById(String name, String email, String telegram, long id) throws ServiceException {
        try {
            userDao.updateNameEmailTelegramById(name, email, telegram, id);
        } catch (DaoException e) {
            logger.error(UPDATE_PROBLEM + e);
            throw new ServiceException(UPDATE_PROBLEM + e);
        }
    }

    public int findUsersAmount() throws ServiceException {
        try {
            return userDao.findUsersAmount();
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(FIND_PROBLEM + e);
        }
    }

    public void updatePhotoByUserId(long userId, String fileName) throws ServiceException {
        try {
            userDao.updatePhotoByUserId(userId, fileName);
        } catch (DaoException e) {
            logger.error(UPDATE_PROBLEM + e);
            throw new ServiceException(UPDATE_PROBLEM + e);
        }
    }

    public void updatePasswordByUserId(long userId, String password) throws ServiceException {
        try {
            userDao.updatePasswordByUserId(userId, password);
        } catch (DaoException e) {
            logger.error(UPDATE_PROBLEM + e);
            throw new ServiceException(UPDATE_PROBLEM + e);
        }
    }


    public User findUserById(long id) throws ServiceException {
        try {
            Optional<User> user = userDao.findUserById(id);
            if (user.isPresent()) {
                return user.get();
            } else throw new ServiceException("Error Service");
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(FIND_PROBLEM + e);
        }
    }

    public List<User> findUsersRange(int offset, int size) throws ServiceException {

        try {
            return userDao.findUsersRange(offset, size);
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(FIND_PROBLEM + e);
        }
    }

    public boolean blockedById(long id) throws ServiceException {
        try {
            return userDao.blockById(id);
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(e);
        }
    }

    public boolean unblockedById(long id) throws ServiceException {
        try {
            return userDao.unblockById(id);
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(UPDATE_PROBLEM + e);
        }

    }


    public boolean isBlockedById(long id) throws ServiceException {
        try {
            return userDao.isBlockedById(id);
        } catch (DaoException e) {
            logger.error(BLOCKED_PROBLEM + e);
            throw new ServiceException(BLOCKED_PROBLEM + e);
        }
    }

    public boolean isUnblockedById(long id) throws ServiceException {
        try {
            return userDao.isUnblockedById(id);
        } catch (DaoException e) {
            logger.error(BLOCKED_PROBLEM + e);
            throw new ServiceException(BLOCKED_PROBLEM + e);
        }
    }

    public User findUserByLogin(String login) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(FIND_PROBLEM + e);
        }
        return user.get();
    }

    public void save(User user) throws ServiceException {
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findUserByLoginPassword(login, password);
        } catch (DaoException e) {
            logger.error(FIND_PROBLEM + e);
            throw new ServiceException(UPDATE_PROBLEM + e);
        }
        return user;
    }

}
