package com.gleb.ratingmovies.controller.command.impl.admin;

import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.UsersPagesWithPagination;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.request.RequestContext;

public class GoToUsersPageCommand implements Command {

    private static final String USERS = "/jsp/pages/users.jsp";
    private static final UsersPagesWithPagination usersPagesWithPagination = UsersPagesWithPagination.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        usersPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(USERS);
    }
}
