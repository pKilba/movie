package com.gleb.ratingmovies.service;

import com.gleb.ratingmovies.exception.DaoException;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.util.Attribute;
import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.entity.UserStatus;
import com.gleb.ratingmovies.dao.impl.UserDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AdminService {

    private static final Logger logger = LogManager.getLogger();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final UserService userService = UserService.getInstance();
    private static final String BLOCKED_USER = "Exception blocked user";
    private static AdminService instance;

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    private AdminService() {
    }


    public boolean isBlockedById(long id) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Optional<User> user;
        try {
            user = userDao.findUserById(id);
        } catch (DaoException e) {
            logger.warn(BLOCKED_USER + e);
            throw new ServiceException(BLOCKED_USER + e);
        }
        return user.filter(value -> value.getUserStatus() == UserStatus.BANNED).isPresent();

    }

    public String banUserById(RequestContext requestContext) throws ServiceException {
        long userId = -1;
        String response = null;
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            userId = ParameterTaker.takeId(requestContext);
        } catch (Exception e) {
            logger.error("Action ban user, id not found. " + e);
            objectNode.put(Attribute.SUCCESS, false);
            response = String.valueOf(objectNode);
        }
        if (response == null) {
            if (!userService.blockedById(userId)) {
                objectNode.put(Attribute.SUCCESS, false);
                logger.warn("User id=" + userId + " not found.");
                throw new ServiceException("User id=" + userId + " not found.");

            } else {
                objectNode.put(Attribute.SUCCESS, true);
                objectNode.put(Attribute.MESSAGE, String.valueOf(UserStatus.BANNED));
            }
            objectNode.put(Attribute.SUCCESS, true);
            objectNode.put("message", "BANNED");
            response = String.valueOf(objectNode);
        }
        return response;
    }

    public String unbanUserById(RequestContext requestContext) throws ServiceException {
        long userId = -1;
        String response = null;
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            userId = ParameterTaker.takeId(requestContext);
        } catch (Exception e) {
            logger.error("Action ban user, id not found. " + e);
            objectNode.put(Attribute.SUCCESS, false);
            response = String.valueOf(objectNode);
        }
        if (response == null) {
            if (!userService.unblockedById(userId)) {
                objectNode.put(Attribute.SUCCESS, false);
                logger.warn("User id=" + userId + " not found.");
                throw new ServiceException("User id=" + userId + " not found.");
            } else {
                objectNode.put(Attribute.SUCCESS, true);
                objectNode.put(Attribute.MESSAGE, String.valueOf(UserStatus.ACTIVE));
            }
            response = String.valueOf(objectNode);
        }
        return response;
    }


}
