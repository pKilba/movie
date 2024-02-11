package com.gleb.ratingmovies.controller.command.impl.general;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.UserService;
import com.gleb.ratingmovies.util.Attribute;


public class GoToUserPageCommand implements Command {

    private static final String USER = "/jsp/pages/profile.jsp";
    private static final UserService userService = UserService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        User user = userService.findUserById(id);
        request.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(USER);
    }
}

